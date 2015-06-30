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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.lang.model.element.Modifier;

/**
 * @author John Leacox
 */
final class Match3MethodPermutationBuilder extends BaseMatchMethodPermutationBuilder {

  private final TypeName input;
  private final TypeName fieldExtractor;
  private final String summaryJavadoc;
  private final String methodName;
  private final TypeName a;
  private final String aName;
  private final TypeName b;
  private final String bName;
  private final TypeName c;
  private final String cName;
  private final int maxArity;
  private final List<TypeNameWithArity> typeAPermutations;
  private final List<TypeNameWithArity> typeBPermutations;
  private final List<TypeNameWithArity> typeCPermutations;

  Match3MethodPermutationBuilder(
      TypeName input, Class<? extends FieldExtractor> inputExtractor, String summaryJavadoc,
      String methodName, TypeName a,
      String aName, TypeName b, String bName, TypeName c, String cName, int maxArity) {
    this.input = input;
    this.fieldExtractor = TypeName.get(inputExtractor);
    this.summaryJavadoc = summaryJavadoc;
    this.methodName = methodName;
    this.a = a;
    this.aName = aName;
    this.b = b;
    this.bName = bName;
    this.c = c;
    this.cName = cName;
    this.maxArity = maxArity;
    this.typeAPermutations = createTypeArityList(a, "A", maxArity);
    this.typeBPermutations = createTypeArityList(b, "B", maxArity);
    this.typeCPermutations = createTypeArityList(c, "C", maxArity);
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
                            c -> MethodSpec.methodBuilder(methodName)
                                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                                .addJavadoc(summaryJavadoc)
                                .addJavadoc(getJavadoc(a, b, c))
                                .returns(getReturnType(inputType, a, b, c))
                                .addTypeVariables(getTypeVariables(inputType, a, b, c))
                                .addParameter(a.typeName, aName)
                                .addParameter(b.typeName, bName)
                                .addParameter(c.typeName, cName)
                                .addStatement(
                                    getMatcherStatement(a, b, c), getMatcherStatementArgs(3))
                                .addStatement(
                                    getReturnStatement(a, b, c),
                                    getReturnStatementArgs(inputType, a, b, c))
                                .build())))
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

    if (firstMatch == MatchType.DECOMPOSE) {
      sb.append("the {@code ").append(aName).append("} value is decomposed to ").append(a.arity);
    } else if (firstMatch == MatchType.ANY) {
      sb.append("the {@code ").append(aName).append("} is extracted");
    }

    if (secondMatch == MatchType.DECOMPOSE) {
      sb.append(" and ");
      sb.append("the {@code ").append(bName).append("} value is decomposed to ").append(b.arity);
    } else if (secondMatch == MatchType.ANY) {
      sb.append(" and ");
      sb.append("the {@code ").append(bName).append("} value is extracted");
    }

    if (thirdMatch == MatchType.DECOMPOSE) {
      sb.append(" and ");
      sb.append("the {@code ").append(cName).append("} value is decomposed to ").append(c.arity);
    } else if (thirdMatch == MatchType.ANY) {
      sb.append(" and ");
      sb.append("the {@code ").append(cName).append("} value is extracted");
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

    List<TypeName> extractedATypes = getExtractedTypes(a.typeName, this.a);
    List<TypeName> extractedBTypes = getExtractedTypes(b.typeName, this.b);
    List<TypeName> extractedCTypes = getExtractedTypes(c.typeName, this.c);

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

    String matchA = getMatcherString(firstMatch, aName);
    String matchB = getMatcherString(secondMatch, bName);
    String matchC = getMatcherString(thirdMatch, cName);

    return "$T matchers = $T.of($T." + matchA + " , $T." + matchB + ", $T." + matchC + ")";
  }

  private Object[] getReturnStatementArgs(
      TypeName inputType, TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    List<TypeName> extractA = getReturnStatementArgs(firstMatch, this.a);
    List<TypeName> extractB = getReturnStatementArgs(secondMatch, this.b);
    List<TypeName> extractC = getReturnStatementArgs(thirdMatch, this.c);

    TypeName[] typeVariables =
        Stream.of(ImmutableList.of(inputType), extractA, extractB, extractC)
            .map(x -> x).flatMap(l -> l.stream()).toArray(s -> new TypeName[s]);

    int arity = a.arity + b.arity + c.arity;
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
    statementArgs.add(fieldExtractor);

    return statementArgs.toArray(new Object[statementArgs.size()]);
  }

  private String getReturnStatement(TypeNameWithArity a, TypeNameWithArity b, TypeNameWithArity c) {
    String indexes = getMatchedIndexes(a, b, c);
    String decompose = getNestedDecomposition(a, b, c);

    MatchType firstMatch = getMatchType(a.typeName);
    MatchType secondMatch = getMatchType(b.typeName);
    MatchType thirdMatch = getMatchType(c.typeName);

    String t;
    if (firstMatch == MatchType.EXACT && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.EXACT) {
      t = "return new $T(matchers, new $T<>())" + decompose;
    } else {
      t = "return new $T(matchers, " + indexes + ", new $T<>())" + decompose;
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
      return ".decomposeFirstAndSecondAndThird(" + aName + ", " + bName + ", " + cName + ")";
    } else if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecond(" + aName + ", " + bName + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.DECOMPOSE
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecond(" + bName + ", " + cName + ")";
    } else if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndSecond(" + aName + ", " + cName + ")";
    } else if (firstMatch == MatchType.DECOMPOSE && secondMatch == MatchType.ANY
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirstAndThird(" + aName + ", " + cName + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.DECOMPOSE
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecondAndThird(" + bName + ", " + cName + ")";
    } else if (firstMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + aName + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + bName + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.ANY
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeThird(" + cName + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + bName + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeFirst(" + cName + ")";
    } else if (firstMatch == MatchType.EXACT && secondMatch == MatchType.ANY
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + cName + ")";
    } else if (firstMatch == MatchType.ANY && secondMatch == MatchType.EXACT
        && thirdMatch == MatchType.DECOMPOSE) {
      return ".decomposeSecond(" + cName + ")";
    } else {
      return "";
    }

    //DecomposableMatchBuilder0<A> a, B b, DecomposableMatchBuilder0<C> c
  }
}
