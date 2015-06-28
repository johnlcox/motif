/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leacox.motif.generator;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.TypeOfCases.typeOf;

import com.leacox.motif.extraction.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.util.Lists;

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

/**
 * @author John Leacox
 */
final class Match2MethodPermutationBuilder extends BaseMatchMethodPermutationBuilder {

  private final TypeName input;
  private final TypeName fieldExtractor;
  private final String methodName;
  private final TypeName a;
  private final String aName;
  private final TypeName b;
  private final String bName;
  private final int maxArity;
  private final List<TypeNameWithArity> typeAPermutations;
  private final List<TypeNameWithArity> typeBPermutations;

  Match2MethodPermutationBuilder(
      TypeName input, Class<? extends FieldExtractor> inputExtractor, String methodName, TypeName a,
      String aName,
      TypeName b, String bName, int maxArity) {
    this.input = input;
    this.fieldExtractor = TypeName.get(inputExtractor);
    this.methodName = methodName;
    this.a = a;
    this.aName = aName;
    this.b = b;
    this.bName = bName;
    this.maxArity = maxArity;
    this.typeAPermutations = createTypeArityList(a, "A", maxArity);
    this.typeBPermutations = createTypeArityList(b, "B", maxArity);
  }

  public List<MethodSpec> build() {
    return getMethodPermutations(input, typeAPermutations, typeBPermutations, methodName);
  }

  private List<MethodSpec> getMethodPermutations(
      TypeName inputType, List<TypeNameWithArity> paramTypesA, List<TypeNameWithArity> paramTypesB,
      String methodName) {

    return paramTypesA.stream()
        .flatMap(
            a -> paramTypesB.stream()
                .filter(b -> a.arity + b.arity <= maxArity)
                .map(
                    b -> MethodSpec.methodBuilder(methodName)
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(getReturnType(inputType, a, b))
                        .addTypeVariables(getTypeVariables(inputType, a, b))
                        .addParameter(a.typeName, aName)
                        .addParameter(b.typeName, bName)
                        .addStatement(getMatcherStatement(a, b), getMatcherStatementArgs(2))
                        .addStatement(
                            getReturnStatement(a, b), getReturnStatementArgs(inputType, a, b))
                        .build()))
        .collect(Collectors.toList());
  }

  private TypeName getReturnType(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b) {
    int arity = a.arity + b.arity;
    ClassName unparameterizedType = ClassName.get(getDecomposableBuilderByArity(arity));

    List<TypeName> extractedATypes = getExtractedTypes(a.typeName, this.a);
    List<TypeName> extractedBTypes = getExtractedTypes(b.typeName, this.b);

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractedATypes, extractedBTypes)
            .map(c -> c).flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    ParameterizedTypeName t = ParameterizedTypeName.get(unparameterizedType, typeVariables);
    return t;
  }

  private Iterable<TypeVariableName> getTypeVariables(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b) {
    return Lists.of(inputType, a.typeName, b.typeName).stream()
        .map(t -> getTypeVariables(t))
        .flatMap(l -> l.stream())
        .distinct()
        .collect(Collectors.toList());
  }

  private String getMatcherStatement(TypeNameWithArity a, TypeNameWithArity b) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    String matchA = getMatcherString(firstMatch, aName);
    String matchB = getMatcherString(secondMatch, bName);

    return "$T matchers = $T.of($T." + matchA + " , $T." + matchB + ")";
  }

  private Object[] getReturnStatementArgs(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b) {

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    List<TypeName> extractA;
    if (firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) {
      extractA = ImmutableList.of(this.a);
    } else {
      extractA = ImmutableList.of();
    }

    List<TypeName> extractB;
    if (secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY) {
      extractB = ImmutableList.of(this.b);
    } else {
      extractB = ImmutableList.of();
    }

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractA, extractB)
            .map(c -> c).flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    TypeName returnType = ParameterizedTypeName
        .get(
            ClassName.get(getDecomposableBuilderByArity(typeVariables.length - 1)),
            typeVariables);

    List<Object> statementArgs = new ArrayList<>();
    statementArgs.add(returnType);
    if (typeVariables.length == 3) { // Includes matched type
      statementArgs.add(Tuple2.class); // Add for index
    }
    statementArgs.add(fieldExtractor);

    return statementArgs.toArray(new Object[statementArgs.size()]);
  }

  private String getReturnStatement(TypeNameWithArity a, TypeNameWithArity b) {
    String indexes = getMatchedIndexes(a, b);
    String decompose = getNestedDecomposition(a, b);

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    if (firstMatch == MatchType.EXACT && secondMatch == MatchType.EXACT) {
      return "return new $T(matchers, new $T<>())" + decompose;
    } else {
      return "return new $T(matchers, " + indexes + ", new $T<>())" + decompose;
    }
  }

  private String getMatchedIndexes(TypeNameWithArity a, TypeNameWithArity b) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    if ((firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) && (
        secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY)) {
      return "$T.of(0, 1)";
    } else if (firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) {
      return "0";
    } else if (secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY) {
      return "1";
    } else {
      return "";
    }
  }

  private String getNestedDecomposition(TypeNameWithArity a, TypeNameWithArity b) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecond(" + aName + ", " + bName + ")";
    } else if (firstMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + aName + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + bName + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + bName + ")";
    } else {
      return "";
    }
  }
}
