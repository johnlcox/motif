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
import com.leacox.motif.MatchesExact;
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
 * Abstract base class for matching permutation builders.
 *
 * @author John Leacox
 */
abstract class BaseMatchMethodPermutationBuilder {
  protected static final ClassName MATCH_EXACT = ClassName.get(MatchesExact.class);
  protected static final ClassName MATCH_ANY = ClassName.get(MatchesAny.class);
  protected static final Map<Integer, Class<? extends DecomposableMatchBuilder>>
      decomposableBuilderByArity = ImmutableMap.of(
      0, DecomposableMatchBuilder0.class, 1, DecomposableMatchBuilder1.class,
      2, DecomposableMatchBuilder2.class, 3, DecomposableMatchBuilder3.class);

  /**
   * Returns a list of type variables for a given type.
   *
   * <p>There are several cases for what the returned type variables will be:
   * <ul>
   *   <li>If {@code t} is a type variable itself, and has no bounds, then a singleton list
   *   containing only {@code t} will be returned.</li>
   *   <li>If {@code t} is a type variable with bounds, then the returned list will first contain
   *   {@code t} followed by the type variables for the bounds</li>
   *   <li>If {@code t} is a parameterized type, then the returned list will contain the type
   *   variables of the parameterized type arguments</li>
   *   <li>Otherwise an empty list is returned</li>
   * </ul>
   */
  protected List<TypeVariableName> getTypeVariables(TypeName t) {
    return match(t)
        .when(typeOf(TypeVariableName.class)).get(
            v -> {
              if (v.bounds.isEmpty()) {
                return ImmutableList.of(v);
              } else {
                return Stream.concat(
                    Stream.of(v), v.bounds.stream()
                        .map(b -> getTypeVariables(b))
                        .flatMap(b -> b.stream()))
                    .collect(Collectors.toList());
              }
            })
        .when(typeOf(ParameterizedTypeName.class)).get(
            p -> p.typeArguments.stream()
                .map(v -> getTypeVariables(v))
                .flatMap(l -> l.stream())
                .collect(Collectors.toList()))
        .orElse(Collections.emptyList())
        .getMatch();
  }

  /**
   * Returns true if the given {@link ClassName} is a decomposable match builder; false otherwise.
   */
  protected boolean isDecomposableBuilder(ClassName t) {
    return t.equals(ClassName.get(DecomposableMatchBuilder0.class))
        || t.equals(ClassName.get(DecomposableMatchBuilder1.class))
        || t.equals(ClassName.get(DecomposableMatchBuilder2.class))
        || t.equals(ClassName.get(DecomposableMatchBuilder3.class));
  }

  /**
   * Returns the decomposable match builder for the given arity.
   */
  protected Class<? extends DecomposableMatchBuilder> getDecomposableBuilderByArity(int arity) {
    return decomposableBuilderByArity.get(arity);
  }

  /**
   * Returns a list of pairs of types and arities up to a given max arity.
   */
  protected List<TypeNameWithArity> createTypeArityList(
      TypeName t, String extractVariableName, int maxArity) {
    TypeName u = match(t)
        .when(typeOf(TypeVariableName.class))
        .get(x -> (TypeName) TypeVariableName.get("E" + x.name, x))
        .orElse(x -> x)
        .getMatch();

    List<TypeNameWithArity> typeArityList = new ArrayList<>();
    //typeArityList.add(TypeNameWithArity.of(t, 0));
    typeArityList.add(TypeNameWithArity.of(ParameterizedTypeName.get(MATCH_EXACT, t), 0));
    typeArityList.add(TypeNameWithArity.of(ParameterizedTypeName.get(MATCH_ANY, t), 1));
    //typeArityList.add(TypeNameWithArity.of(TypeName.get(MatchesAny.class), 1));
    IntStream.rangeClosed(0, maxArity).forEach(
        extractArity -> {
          TypeName[] typeVariables = createTypeVariables(u, extractVariableName, extractArity);
          typeArityList.add(
              TypeNameWithArity.of(
                  ParameterizedTypeName
                      .get(
                          ClassName.get(getDecomposableBuilderByArity(extractArity)),
                          typeVariables), extractArity));
        });

    return typeArityList;
  }

  /**
   * Returns an array of type variables for a given type and arity.
   */
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

  /**
   * Returns the statement arguments for the match method matcher statement.
   */
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

  /**
   * Returns the type of match to use for a given type.
   */
  protected MatchType getMatchType(TypeName m) {
    return match(m)
        .when(typeOf(ParameterizedTypeName.class)).get(
            t -> {
              if (isDecomposableBuilder(t.rawType)) {
                return MatchType.DECOMPOSE;
              } else if (t.rawType.equals(MATCH_ANY)) {
                return MatchType.ANY;
              } else if (t.rawType.equals(MATCH_EXACT)) {
                return MatchType.EXACT;
              } else {
                return MatchType.EXACT;
              }
            })
        .when(typeOf(TypeVariableName.class)).get(t -> MatchType.EXACT)
        .orElse(MatchType.ANY)
        .getMatch();
  }

  /**
   * Returns the matcher string {@code any() or eq(x)} for a given type.
   */
  protected String getMatcherString(MatchType matchType, String paramName) {
    String matchB;
    if (matchType == MatchType.DECOMPOSE || matchType == MatchType.ANY) {
      matchB = "any()";
    } else {
      matchB = "eq(" + paramName + ".t)";
    }
    return matchB;
  }

  /**
   * Returns the extracted type parameters.
   */
  protected List<TypeName> getExtractedTypes(TypeName permutationType, TypeName paramType) {
    return match(permutationType)
        .when(typeOf(ParameterizedTypeName.class)).get(
            t -> {
              if (isDecomposableBuilder(t.rawType)) {
                return t.typeArguments.subList(1, t.typeArguments.size());
              } else if (t.rawType.equals(MATCH_ANY)) {
                return ImmutableList.of(paramType);
              } else {
                return Collections.<TypeName>emptyList();
              }
            })
        .when(typeOf(TypeVariableName.class)).get(t -> ImmutableList.of())
        .when(typeOf(ClassName.class)).get(t -> ImmutableList.of(paramType))
        .orElse(t -> ImmutableList.of(t))
        .getMatch();
  }

  /**
   * Returns the statement arguments for the match method returns statement.
   */
  protected List<TypeName> getReturnStatementArgs(MatchType matchType, TypeName paramType) {
    List<TypeName> extractA;
    if (matchType == MatchType.DECOMPOSE) {
      TypeName u = match(paramType)
          .when(typeOf(TypeVariableName.class)).get(
              x -> (TypeName) TypeVariableName.get("E" + x.name, x))
          .orElse(x -> x)
          .getMatch();

      extractA = ImmutableList.of(u);
    } else if (matchType == MatchType.ANY) {
      extractA = ImmutableList.of(paramType);
    } else {
      extractA = ImmutableList.of();
    }
    return extractA;
  }
}
