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

package com.leacox.motif.extract;

import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Maps;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A decomposable match builder that extracts 1 parameter.
 *
 * @author John Leacox
 */
public final class DecomposableMatchBuilder1<T, A> extends DecomposableMatchBuilder<T> {
  final List<Matcher<Object>> fieldMatchers;
  final int extractedIndex;
  final FieldExtractor<T> fieldExtractor;

  /**
   * Creates a new instance of {@link DecomposableMatchBuilder1}.
   */
  public DecomposableMatchBuilder1(
      List<Matcher<Object>> fieldMatchers, int extractedIndex,
      FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);

    this.fieldMatchers = fieldMatchers;
    this.extractedIndex = extractedIndex;
    this.fieldExtractor = fieldExtractor;
  }

  /**
   * Builds a {@link DecomposableMatch1}.
   */
  public DecomposableMatch1<T, A> build() {
    return new DecomposableMatch1<>(fieldMatchers, extractedIndex, fieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed and 0 elements.
   */
  public DecomposableMatchBuilder0<T> decomposeFirst(DecomposableMatchBuilder0<A> first) {
    Objects.requireNonNull(first);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex = Maps.treeMapOf(extractedIndex, first);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder0<>(matchers, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 1 element.
   */
  public <B> DecomposableMatchBuilder1<T, B> decomposeFirst(
      DecomposableMatchBuilder1<A, B> first) {
    Objects.requireNonNull(first);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex = Maps.treeMapOf(extractedIndex, first);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    int newIndex = extractedIndex + first.extractedIndex;

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(matchers, newIndex, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 2 elements.
   */
  public <B, C> DecomposableMatchBuilder2<T, B, C> decomposeFirst(
      DecomposableMatchBuilder2<A, B, C> first) {
    Objects.requireNonNull(first);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex = Maps.treeMapOf(extractedIndex, first);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndex + first.extractedIndexes.first(),
        extractedIndex + first.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 3 elements.
   */
  public <B, C, D> DecomposableMatchBuilder3<T, B, C, D> decomposeFirst(
      DecomposableMatchBuilder3<A, B, C, D> first) {
    Objects.requireNonNull(first);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex = Maps.treeMapOf(extractedIndex, first);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndex + first.extractedIndexes.first(),
            extractedIndex + first.extractedIndexes.second(),
            extractedIndex + first.extractedIndexes.third());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
}
