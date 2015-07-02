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
import com.leacox.motif.tuple.Tuple3;

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
final class Match3MethodPermutationBuilder extends BaseMatchMethodPermutationBuilder {
  private final TypeName input;
  private final String methodName;
  private final String summaryJavadoc;
  private final List<MethodParam> nonMatchParams;
  private final Tuple2<Class<? extends FieldExtractor>, Object[]> fieldExtractorWithArgs;
  private final MethodParam paramA;
  private final MethodParam paramB;
  private final MethodParam paramC;
  private final int maxArity;
  private final List<TypeNameWithArity> typeAPermutations;
  private final List<TypeNameWithArity> typeBPermutations;
  private final List<TypeNameWithArity> typeCPermutations;

  Match3MethodPermutationBuilder(TypeName input, Match3MethodSpec match3MethodSpec, int maxArity) {
    this.input = input;
    this.methodName = match3MethodSpec.name;
    this.summaryJavadoc = match3MethodSpec.summaryJavadoc;
    this.nonMatchParams = match3MethodSpec.nonMatchParams;
    this.fieldExtractorWithArgs = match3MethodSpec.fieldExtractorWithArgs;
    this.paramA = match3MethodSpec.paramA;
    this.paramB = match3MethodSpec.paramB;
    this.paramC = match3MethodSpec.paramC;
    this.maxArity = maxArity;
    this.typeAPermutations = createTypeArityList(paramA.type, "A", maxArity);
    this.typeBPermutations = createTypeArityList(paramB.type, "B", maxArity);
    this.typeCPermutations = createTypeArityList(paramC.type, "C", maxArity);
  }

  public List<MethodSpec> build() {
    return getMethodPermutations(input, typeAPermutations, typeBPermutations, typeCPermutations);
  }

  private List<MethodSpec> getMethodPermutations(
      TypeName inputType, List<TypeNameWithArity> paramTypesA, List<TypeNameWithArity> paramTypesB,
      List<TypeNameWithArity> paramTypesC) {

    return paramTypesA.stream()
        .flatMap(
            a -> paramTypesB.stream()
                .flatMap(
                    b -> paramTypesC.stream()
                        .filter(c -> a.arity + b.arity + c.arity <= maxArity)
                        .map(
                            c -> {
                              MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder(
                                  methodName)
                                  .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                  .addJavadoc(summaryJavadoc)
                                  .addJavadoc(getJavadoc(a, b, c))
                                  .returns(getReturnType(inputType, a, b, c))
                                  .addTypeVariables(getTypeVariables(inputType, a, b, c));

                              if (nonMatchParams != null && !nonMatchParams.isEmpty()) {
                                nonMatchParams.stream().forEach(
                                    p -> methodBuilder.addParameter(p.type, p.name));
                              }

                              return methodBuilder.addParameter(a.typeName, paramA.name)
                                  .addParameter(b.typeName, paramB.name)
                                  .addParameter(c.typeName, paramC.name)
                                  .addStatement(
                                      getMatcherStatement(a, b, c), getMatcherStatementArgs(3))
                                  .addStatement(
                                      getReturnStatement(a, b, c),
                                      getReturnStatementArgs(inputType, a, b, c))
                                  .build();
                            }
                        )
                )
        )
        .collect(Collectors.toList());
  }

  private String getJavadoc(TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    StringBuilder sb = new StringBuilder();

    if (firstMatch != MatchType.EXACT || secondMatch != MatchType.EXACT
        || thirdMatch != MatchType.EXACT) {
      sb.append("\n");
      sb.append("<p>If matched, ");
    }

    boolean isAnd = false;

    if (firstMatch == MatchType.DECOMPOSE) {
      sb.append("the {@code ").append(paramA.name).append("} value is decomposed to ")
          .append(a.arity);
      isAnd = true;
    } else if (firstMatch == MatchType.ANY) {
      sb.append("the {@code ").append(paramA.name).append("} is extracted");
      isAnd = true;
    }

    if (secondMatch == MatchType.DECOMPOSE) {
      if (isAnd) {
        sb.append(" and ");
      }
      sb.append("the {@code ").append(paramB.name).append("} value is decomposed to ")
          .append(b.arity);
      isAnd = true;
    } else if (secondMatch == MatchType.ANY) {
      if (isAnd) {
        sb.append(" and ");
      }
      sb.append("the {@code ").append(paramB.name).append("} value is extracted");
      isAnd = true;
    }

    if (thirdMatch == MatchType.DECOMPOSE) {
      if (isAnd) {
        sb.append(" and ");
      }
      sb.append("the {@code ").append(paramC.name).append("} value is decomposed to ")
          .append(c.arity);
    } else if (thirdMatch == MatchType.ANY) {
      if (isAnd) {
        sb.append(" and ");
      }
      sb.append("the {@code ").append(paramC.name).append("} value is extracted");
    }

    if (firstMatch != MatchType.EXACT || secondMatch != MatchType.EXACT
        || thirdMatch != MatchType.EXACT) {
      sb.append(".");
      sb.append("\n");
    }

    int i = 0;
    while ((i = sb.indexOf(" ", i + 100)) != -1) {
      sb.replace(i, i + 1, "\n");
    }

    sb.append("\n");

    return sb.toString();
  }

  private TypeName getReturnType(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    int arity = a.arity + b.arity + c.arity;
    ClassName unparameterizedType = ClassName.get(getDecomposableBuilderByArity(arity));

    List<TypeName> extractedATypes = getExtractedTypes(a.typeName, paramA.type);
    List<TypeName> extractedBTypes = getExtractedTypes(b.typeName, paramB.type);
    List<TypeName> extractedCTypes = getExtractedTypes(c.typeName, paramC.type);

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractedATypes, extractedBTypes, extractedCTypes)
            .flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    ParameterizedTypeName t = ParameterizedTypeName.get(unparameterizedType, typeVariables);
    return t;
  }

  private Iterable<TypeVariableName> getTypeVariables(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    return ImmutableList.of(inputType, a.typeName, b.typeName, c.typeName).stream()
        .map(t -> getTypeVariables(t))
        .flatMap(l -> l.stream())
        .distinct()
        .collect(Collectors.toList());
  }

  private String getMatcherStatement(
      TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    String matchA = getMatcherString(firstMatch, paramA.name);
    String matchB = getMatcherString(secondMatch, paramB.name);
    String matchC = getMatcherString(thirdMatch, paramC.name);

    return "$T matchers = $T.of($T." + matchA + ", $T." + matchB + ", $T." + matchC + ")";
  }

  private Object[] getReturnStatementArgs(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    List<TypeName> extractA = getReturnStatementArgs(firstMatch, paramA.type);
    List<TypeName> extractB = getReturnStatementArgs(secondMatch, paramB.type);
    List<TypeName> extractC = getReturnStatementArgs(thirdMatch, paramC.type);

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractA, extractB, extractC)
            .map(x -> x).flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    TypeName returnType = ParameterizedTypeName
        .get(
            ClassName.get(getDecomposableBuilderByArity(typeVariables.length - 1)),
            typeVariables);

    List<Object> statementArgs = new ArrayList<>();
    statementArgs.add(returnType);
    if (typeVariables.length == 3) {
      statementArgs.add(Tuple2.class);
    } else if (typeVariables.length == 4) { // Includes matched type
      statementArgs.add(Tuple3.class); // Add for index
    }
    statementArgs.add(fieldExtractorWithArgs.first());

    return statementArgs.toArray(new Object[statementArgs.size()]);
  }

  private String getReturnStatement(TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    String indexes = getMatchedIndexes(a, b, c);
    String decompose = getNestedDecomposition(a, b, c);

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    String args = "";
    if (fieldExtractorWithArgs.second().length > 0) {
      args = Arrays.stream(fieldExtractorWithArgs.second())
          .map(x -> x.toString())
          .collect(Collectors.joining(", "));
    }

    String t;
    if (firstMatch == MatchType.EXACT && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.EXACT) {
      t = "return new $T(matchers, new $T<>(" + args + "))" + decompose;
    } else {
      t = "return new $T(matchers, " + indexes + ", new $T<>(" + args + "))" + decompose;
    }

    return t;
  }

  private String getMatchedIndexes(TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    if ((firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) &&
        (secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY) &&
        (thirdMatch == MatchType.DECOMPOSE || thirdMatch == MatchType.ANY)) {
      return "$T.of(0, 1, 2)";
    } else if ((firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) &&
        (secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY)) {
      return "$T.of(0, 1)";
    } else if ((firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) &&
        (thirdMatch == MatchType.DECOMPOSE || thirdMatch == MatchType.ANY)) {
      return "$T.of(0, 2)";
    } else if ((secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY) &&
        (thirdMatch == MatchType.DECOMPOSE || thirdMatch == MatchType.ANY)) {
      return "$T.of(1, 2)";
    } else if (firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) {
      return "0";
    } else if (secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY) {
      return "1";
    } else if (thirdMatch == MatchType.DECOMPOSE || thirdMatch == MatchType.ANY) {
      return "2";
    } else {
      return "";
    }
  }

  private String getNestedDecomposition(
      TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.DECOMPOSE
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecondAndThird(" + paramA.name + ", " + paramB.name + ", "
          + paramC.name + ")";
    } else if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecond(" + paramA.name + ", " + paramB.name + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.DECOMPOSE
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecond(" + paramB.name + ", " + paramC.name + ")";
    } else if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecond(" + paramA.name + ", " + paramC.name + ")";
    } else if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.ANY
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndThird(" + paramA.name + ", " + paramC.name + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.DECOMPOSE
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecondAndThird(" + paramB.name + ", " + paramC.name + ")";
    } else if (firstMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + paramA.name + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + paramB.name + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.ANY
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeThird(" + paramC.name + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + paramB.name + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + paramC.name + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.ANY
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + paramC.name + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + paramC.name + ")";
    } else {
      return "";
    }

    //DecomposableMatchBuilder0<A> a, B b, DecomposableMatchBuilder0<C> c
  }
}
