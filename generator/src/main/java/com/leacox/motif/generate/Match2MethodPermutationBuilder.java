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
import com.leacox.motif.tuple.Tuple2;

import com.google.common.collect.ImmutableList;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

/**
 * @author John Leacox
 */
final class Match2MethodPermutationBuilder extends BaseMatchMethodPermutationBuilder {
  private final TypeName input;
  private final String methodName;
  private final String summaryJavadoc;
  private final List<MethodParam> nonMatchParams;
  private final Tuple2<Class<? extends FieldExtractor>, Object[]> fieldExtractorWithArgs;
  private final MethodParam paramA;
  private final MethodParam paramB;
  private final int maxArity;
  private final List<TypeNameWithArity> typeAPermutations;
  private final List<TypeNameWithArity> typeBPermutations;

  Match2MethodPermutationBuilder(
      TypeName input, Match2MethodSpec match2MethodSpec, int maxArity) {
    this.input = input;
    this.methodName = match2MethodSpec.name;
    this.summaryJavadoc = match2MethodSpec.summaryJavadoc;
    this.nonMatchParams = match2MethodSpec.nonMatchParams;
    this.fieldExtractorWithArgs = match2MethodSpec.fieldExtractorWithArgs;
    this.paramA = match2MethodSpec.paramA;
    this.paramB = match2MethodSpec.paramB;
    this.maxArity = maxArity;
    this.typeAPermutations = createTypeArityList(paramA.type, "A", maxArity);
    this.typeBPermutations = createTypeArityList(paramB.type, "B", maxArity);
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
                    b -> {
                      MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(methodName)
                          .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                          .addJavadoc(summaryJavadoc)
                          .addJavadoc(getJavadoc(a, b))
                          .returns(getReturnType(inputType, a, b))
                          .addTypeVariables(getTypeVariables(inputType, a, b));

                      if (nonMatchParams != null && !nonMatchParams.isEmpty()) {
                        nonMatchParams.stream().forEach(
                            p -> methodBuilder.addParameter(p.type, p.name));
                      }

                      return methodBuilder.addParameter(a.typeName, paramA.name)
                          .addParameter(b.typeName, paramB.name)
                          .addStatement(getMatcherStatement(a, b), getMatcherStatementArgs(2))
                          .addStatement(
                              getReturnStatement(a, b), getReturnStatementArgs(inputType, a, b))
                          .build();
                    }
                )
        )
        .collect(Collectors.toList());
  }

  private String getJavadoc(TypeNameWithArity a, TypeNameWithArity b) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    StringBuilder sb = new StringBuilder();

    if (firstMatch != MatchType.EXACT || secondMatch != MatchType.EXACT) {
      sb.append("\n");
      sb.append("<p>If matched, ");
    }

    boolean isAnd = false;

    if (firstMatch == MatchType.DECOMPOSE) {
      sb.append("the {@code ").append(paramA.name).append("} value is decomposed to ")
          .append(a.arity);
      isAnd = true;
    } else if (firstMatch == MatchType.ANY) {
      sb.append("the {@code ").append(paramA.name).append("} value is extracted");
      isAnd = true;
    }

    if (secondMatch == MatchType.DECOMPOSE) {
      if (isAnd) {
        sb.append(" and ");
      }
      sb.append("the {@code ").append(paramB.name).append("} value is decomposed to ")
          .append(b.arity);
    } else if (secondMatch == MatchType.ANY) {
      if (isAnd) {
        sb.append(" and ");
      }
      sb.append("the {@code ").append(paramB.name).append("} value is extracted");
    }

    if (firstMatch != MatchType.EXACT || secondMatch != MatchType.EXACT) {
      sb.append(".");
      sb.append("\n");
    }

    int i = 0;
    while ((i = sb.indexOf(" ", i + 100)) != -1) {
      sb.replace(i, i + 1, "\n");
    }

    return sb.toString();
  }

  private TypeName getReturnType(TypeName inputType, TypeNameWithArity a, TypeNameWithArity b) {
    int arity = a.arity + b.arity;
    ClassName unparameterizedType = ClassName.get(getDecomposableBuilderByArity(arity));

    List<TypeName> extractedATypes = getExtractedTypes(a.typeName, paramA.type);
    List<TypeName> extractedBTypes = getExtractedTypes(b.typeName, paramB.type);

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractedATypes, extractedBTypes)
            .map(c -> c).flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    ParameterizedTypeName t = ParameterizedTypeName.get(unparameterizedType, typeVariables);
    return t;
  }

  private Iterable<TypeVariableName> getTypeVariables(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b) {
    return ImmutableList.of(inputType, a.typeName, b.typeName).stream()
        .map(t -> getTypeVariables(t))
        .flatMap(l -> l.stream())
        .distinct()
        .collect(Collectors.toList());
  }

  private String getMatcherStatement(TypeNameWithArity a, TypeNameWithArity b) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    String matchA = getMatcherString(firstMatch, paramA.name);
    String matchB = getMatcherString(secondMatch, paramB.name);

    return "$T matchers = $T.of($T." + matchA + ", $T." + matchB + ")";
  }

  private Object[] getReturnStatementArgs(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b) {

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    List<TypeName> extractA = getReturnStatementArgs(firstMatch, paramA.type);
    List<TypeName> extractB = getReturnStatementArgs(secondMatch, paramB.type);

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
    statementArgs.add(fieldExtractorWithArgs.first());

    return statementArgs.toArray(new Object[statementArgs.size()]);
  }

  private String getReturnStatement(TypeNameWithArity a, TypeNameWithArity b) {
    String indexes = getMatchedIndexes(a, b);
    String decompose = getNestedDecomposition(a, b);

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);

    String args = "";
    if (fieldExtractorWithArgs.second().length > 0) {
      args = Arrays.stream(fieldExtractorWithArgs.second())
          .map(x -> x.toString())
          .collect(Collectors.joining(", "));
    }

    if (firstMatch == MatchType.EXACT && secondMatch == MatchType.EXACT) {
      return "return new $T(matchers, new $T<>(" + args + "))" + decompose;
    } else if (fieldExtractorWithArgs.second().length > 0) {
      return "return new $T(matchers, " + indexes + ", new $T<>(" + args + "))" + decompose;
    } else {
      return "return new $T(matchers, " + indexes + ", new $T<>(" + args + "))" + decompose;
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
      return ".decomposeFirstAndSecond(" + paramA.name + ", " + paramB.name + ")";
    } else if (firstMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + paramA.name + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + paramB.name + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + paramB.name + ")";
    } else {
      return "";
    }
  }
}
