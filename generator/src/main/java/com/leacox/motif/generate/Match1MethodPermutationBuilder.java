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
package com.leacox.motif.generate;

import com.leacox.motif.extract.FieldExtractor;

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

/**
 * @author John Leacox
 */
final class Match1MethodPermutationBuilder extends BaseMatchMethodPermutationBuilder {
  private final TypeName input;
  private final TypeName fieldExtractor;
  private final String summaryJavadoc;
  private final String methodName;
  private final TypeName a;
  private final String aName;
  private final int maxArity;
  private final List<TypeNameWithArity> typeAPermutations;

  Match1MethodPermutationBuilder(
      TypeName input, Class<? extends FieldExtractor> inputExtractor, String summaryJavadoc,
      String methodName, TypeName a,
      String aName, int maxArity) {
    this.input = input;
    this.fieldExtractor = TypeName.get(inputExtractor);
    this.summaryJavadoc = summaryJavadoc;
    this.methodName = methodName;
    this.a = a;
    this.aName = aName;
    this.maxArity = maxArity;
    this.typeAPermutations = createTypeArityList(a, "A", maxArity);
  }

  public List<MethodSpec> build() {
    return getMethodPermutations(input, typeAPermutations, methodName);
  }

  private List<MethodSpec> getMethodPermutations(
      TypeName inputType, List<TypeNameWithArity> paramTypesA, String methodName) {

    return paramTypesA.stream()
        .filter(a -> a.arity <= maxArity)
        .map(
            a -> MethodSpec.methodBuilder(methodName)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addJavadoc(summaryJavadoc)
                .addJavadoc(getJavadoc(a))
                .returns(getReturnType(inputType, a))
                .addTypeVariables(getTypeVariables(inputType, a))
                .addParameter(a.typeName, aName)
                .addStatement(getMatcherStatement(a), getMatcherStatementArgs(1))
                .addStatement(getReturnStatement(a), getReturnStatementArgs(inputType, a))
                .build())
        .collect(Collectors.toList());
  }

  private String getJavadoc(TypeNameWithArity a) {
    MatchType firstMatch = getMatchType(a.typeName);

    StringBuilder sb = new StringBuilder();

    if (firstMatch != MatchType.EXACT) {
      sb.append("\n");
      sb.append("<p>If matched, ");
    }

    if (firstMatch == MatchType.DECOMPOSE) {
      sb.append("the {@code ").append(aName).append("} value is decomposed to ").append(a.arity);
    } else if (firstMatch == MatchType.ANY) {
      sb.append("the {@code ").append(aName).append("} value is extracted");
    }

    if (firstMatch != MatchType.EXACT) {
      sb.append(".");
      sb.append("\n");
    }

    int i = 0;
    while ((i = sb.indexOf(" ", i + 100)) != -1) {
      sb.replace(i, i + 1, "\n");
    }

    return sb.toString();
  }

  private TypeName getReturnType(TypeName inputType, TypeNameWithArity a) {
    int arity = a.arity;
    ClassName unparameterizedType = ClassName.get(getDecomposableBuilderByArity(arity));

    List<TypeName> extractedATypes = getExtractedTypes(a.typeName, this.a);

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractedATypes)
            .map(c -> c).flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    ParameterizedTypeName t = ParameterizedTypeName.get(unparameterizedType, typeVariables);
    return t;
  }

  private Iterable<TypeVariableName> getTypeVariables(TypeName inputType, TypeNameWithArity a) {
    return ImmutableList.of(inputType, a.typeName).stream()
        .map(t -> getTypeVariables(t))
        .flatMap(l -> l.stream())
        .distinct()
        .collect(Collectors.toList());
  }

  private String getMatcherStatement(TypeNameWithArity a) {
    MatchType firstMatch = getMatchType(a.typeName);

    String matchA = getMatcherString(firstMatch, aName);

    return "$T matchers = $T.of($T." + matchA + ")";
  }

  private Object[] getReturnStatementArgs(TypeName inputType, TypeNameWithArity a) {
    MatchType firstMatch = getMatchType(a.typeName);

    List<TypeName> extractA;
    if (firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) {
      extractA = ImmutableList.of(this.a);
    } else {
      extractA = ImmutableList.of();
    }

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractA)
            .map(c -> c).flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    TypeName returnType = ParameterizedTypeName
        .get(
            ClassName.get(getDecomposableBuilderByArity(typeVariables.length - 1)),
            typeVariables);

    List<Object> statementArgs = new ArrayList<>();
    statementArgs.add(returnType);
    statementArgs.add(fieldExtractor);

    return statementArgs.toArray(new Object[statementArgs.size()]);
  }

  private String getReturnStatement(TypeNameWithArity a) {
    String indexes = getMatchedIndexes(a);
    String decompose = getNestedDecomposition(a);

    MatchType firstMatch = getMatchType(a.typeName);

    if (firstMatch == MatchType.EXACT) {
      return "return new $T(matchers, new $T<>())" + decompose;
    } else {
      return "return new $T(matchers, " + indexes + ", new $T<>())" + decompose;
    }
  }

  private String getMatchedIndexes(TypeNameWithArity a) {
    MatchType firstMatch = getMatchType(a.typeName);

    if (firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) {
      return "0";
    } else {
      return "";
    }
  }

  private String getNestedDecomposition(TypeNameWithArity a) {
    MatchType firstMatch = getMatchType(a.typeName);

    if (firstMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + aName + ")";
    } else {
      return "";
    }
  }
}
