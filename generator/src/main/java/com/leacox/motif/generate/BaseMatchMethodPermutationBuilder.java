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

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.TypeOfCases.typeOf;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.extract.DecomposableMatchBuilder;
import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.DecomposableMatchBuilder3;
import com.leacox.motif.extract.matchers.ArgumentMatchers;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeVariableName;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author John Leacox
 */
abstract class BaseMatchMethodPermutationBuilder {
  protected static final TypeName MATCH_ANY = TypeName.get(MatchesAny.class);
  protected static final Map<Integer, Class<? extends DecomposableMatchBuilder>>
      decomposableBuilderByArity = ImmutableMap.of(
      0, DecomposableMatchBuilder0.class, 1, DecomposableMatchBuilder1.class,
      2, DecomposableMatchBuilder2.class, 3, DecomposableMatchBuilder3.class);

  protected List<TypeVariableName> getTypeVariables(TypeName t) {
    return match(t)
        .when(typeOf(TypeVariableName.class)).get(v -> Lists.of(v))
        .when(typeOf(ParameterizedTypeName.class)).get(
            p -> p.typeArguments.stream()
                .map(v -> getTypeVariables(v))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList()))
        .orElse(Collections.emptyList())
        .getMatch();
  }

  protected boolean isDecomposableBuilder(ClassName t) {
    return t.equals(ClassName.get(DecomposableMatchBuilder0.class))
        || t.equals(ClassName.get(DecomposableMatchBuilder1.class))
        || t.equals(ClassName.get(DecomposableMatchBuilder2.class))
        || t.equals(ClassName.get(DecomposableMatchBuilder3.class));
  }

  protected Class<? extends DecomposableMatchBuilder> getDecomposableBuilderByArity(int arity) {
    return decomposableBuilderByArity.get(arity);
  }

  protected List<TypeNameWithArity> createTypeArityList(
      TypeName t, String extractVariableName, int maxArity) {
    List<TypeNameWithArity> typeArityList = new ArrayList<>();
    typeArityList.add(TypeNameWithArity.of(t, 0));
    typeArityList.add(TypeNameWithArity.of(MATCH_ANY, 1));
    IntStream.rangeClosed(0, maxArity).forEach(
        extractArity -> {
          TypeName[] typeVariables = createTypeVariables(t, extractVariableName, extractArity);
          typeArityList.add(
              TypeNameWithArity.of(
                  ParameterizedTypeName
                      .get(
                          ClassName.get(getDecomposableBuilderByArity(extractArity)),
                          typeVariables), extractArity));
        });

    return typeArityList;
  }

  protected TypeName[] createTypeVariables(
      TypeName extractedType, String extractVariableName, int extractArity) {
    List<TypeName> typeVariables = new ArrayList<>();
    typeVariables.add(extractedType);
    if (extractArity > 0) {
      typeVariables.addAll(
          IntStream.rangeClosed(1, extractArity)
              .mapToObj(j -> TypeVariableName.get(extractVariableName + j))
              .collect(Collectors.toList()));
    }

    return typeVariables.toArray(new TypeName[typeVariables.size()]);
  }

  protected static Object[] getMatcherStatementArgs(int matchedCount) {
    TypeName matcher = ParameterizedTypeName.get(ClassName.get(Matcher.class), TypeName.OBJECT);
    TypeName listOfMatchers = ParameterizedTypeName.get(ClassName.get(List.class), matcher);
    TypeName lists = TypeName.get(Lists.class);
    TypeName argumentMatchers = TypeName.get(ArgumentMatchers.class);

    return Stream.concat(
        ImmutableList.of(listOfMatchers, lists).stream(),
        IntStream.range(0, matchedCount)
            .mapToObj(i -> argumentMatchers)
    ).toArray(s -> new TypeName[s]);
  }

  protected MatchType getMatchType(TypeName m) {
    return match(m)
        .when(typeOf(ParameterizedTypeName.class)).get(
            t -> {
              if (isDecomposableBuilder(t.rawType)) {
                return MatchType.DECOMPOSE;
              } else {
                return MatchType.EXACT;
              }
            })
        .when(typeOf(TypeVariableName.class)).get(t -> MatchType.EXACT)
        .orElse(MatchType.ANY)
        .getMatch();
  }

  protected String getMatcherString(MatchType secondMatch, String paramName) {
    String matchB;
    if (secondMatch == MatchType.DECOMPOSE || secondMatch == MatchType.ANY) {
      matchB = "any()";
    } else {
      matchB = "eq(" + paramName + ")";
    }
    return matchB;
  }

  protected List<TypeName> getExtractedTypes(TypeName permutationType, TypeName paramType) {
    return match(permutationType)
        .when(typeOf(ParameterizedTypeName.class)).get(
            t -> {
              if (isDecomposableBuilder(t.rawType)) {
                return t.typeArguments.subList(1, t.typeArguments.size());
              } else {
                return Collections.<TypeName>emptyList();
              }
            })
        .when(typeOf(TypeVariableName.class)).get(t -> ImmutableList.of())
        .when(typeOf(ClassName.class)).get(t -> ImmutableList.of(paramType))
        .orElse(t -> ImmutableList.of(t))
        .getMatch();
  }

  protected List<TypeName> getReturnStatementArgs(MatchType firstMatch, TypeName paramType) {
    List<TypeName> extractA;
    if (firstMatch == MatchType.DECOMPOSE || firstMatch == MatchType.ANY) {
      extractA = ImmutableList.of(paramType);
    } else {
      extractA = ImmutableList.of();
    }
    return extractA;
  }
}
