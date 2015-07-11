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
 * A decomposable match builder that extracts 2 parameters.
 *
 * @author John Leacox
 */
public final class DecomposableMatchBuilder2<T, A, B> extends DecomposableMatchBuilder<T> {
  final Tuple2<Integer, Integer> extractedIndexes;

  /**
   * Creates a new instance of {@link DecomposableMatchBuilder2}.
   */
  public DecomposableMatchBuilder2(
      List<Matcher<Object>> fieldMatchers, Tuple2<Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);

    this.extractedIndexes = extractedIndexes;
  }

  /**
   * Builds a {@link DecomposableMatch2}.
   */
  public DecomposableMatch2<T, A, B> build() {
    return new DecomposableMatch2<>(fieldMatchers, extractedIndexes, fieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 0 elements.
   */
  public DecomposableMatchBuilder1<T, B> decomposeFirst(DecomposableMatchBuilder0<A> a) {
    Objects.requireNonNull(a);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.second(), nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 1 element.
   */
  public <A1> DecomposableMatchBuilder2<T, A1, B> decomposeFirst(
      DecomposableMatchBuilder1<A, A1> a) {
    Objects.requireNonNull(a);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            this.extractedIndexes.first() + a.extractedIndex, this.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 2 elements.
   */
  public <A1, A2> DecomposableMatchBuilder3<T, A1, A2, B> decomposeFirst(
      DecomposableMatchBuilder2<A, A1, A2> a) {
    Objects.requireNonNull(a);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            this.extractedIndexes.first() + a.extractedIndexes.first(),
            this.extractedIndexes.first() + a.extractedIndexes.second(),
            a.fieldMatchers.size() - 1 + this.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  // TODO: Is there a good way to avoid all the duplication and pull all the decomposeFirst methods
  // up to the parent class and let the children generate the correct indexes?
  //private <U extends DecomposableMatchBuilder<T>, A1, A2> U decomposeFirst(Class<U> clazz,
  // DecomposableMatchBuilder a) {
  //  Objects.requireNonNull(a);
  //
  //  Map<Integer, DecomposableMatchBuilder> buildersByIndex =
  //      Maps.treeMapOf(extractedIndexes.first(), a);
  //
  //  List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);
  //
  //  NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);
  //
  //  if (a instanceof DecomposableMatchBuilder0) {
  //    return clazz.cast(new DecomposableMatchBuilder1<>(matchers, extractedIndexes.second(),
  // nestedFieldExtractor));
  //  } else if (a instanceof DecomposableMatchBuilder1) {
  //    DecomposableMatchBuilder1<A, A1> x = (DecomposableMatchBuilder1<A, A1>) a;
  //
  //    Tuple2<Integer, Integer> newIndexes =
  //        Tuple2.of(
  //            this.extractedIndexes.first() + x.extractedIndex, this.extractedIndexes.second());
  //
  //    return clazz.cast(new DecomposableMatchBuilder2<>(matchers, newIndexes,
  // nestedFieldExtractor));
  //  } else if (a instanceof DecomposableMatchBuilder2) {
  //    DecomposableMatchBuilder2<A, A1, A2> x = (DecomposableMatchBuilder2<A, A1, A2>) a;
  //
  //    Tuple3<Integer, Integer, Integer> newIndexes =
  //        Tuple3.of(
  //            this.extractedIndexes.first() + x.extractedIndexes.first(),
  //            this.extractedIndexes.first() + x.extractedIndexes.second(),
  //            a.fieldMatchers.size() - 1 + this.extractedIndexes.second());
  //
  //    return clazz.cast(new DecomposableMatchBuilder3<>(matchers, newIndexes,
  // nestedFieldExtractor));
  //  }
  //
  //  throw new IllegalStateException("Unknown Decomposition pattern");
  //}

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the second element of
   * this match builder.
   *
   * <p>The second element is decomposed to 0 elements.
   */
  public DecomposableMatchBuilder1<T, A> decomposeSecond(DecomposableMatchBuilder0<B> second) {
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.first(), nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the second element of
   * this match builder.
   *
   * <p>The second element is decomposed to 1 elements.
   */
  public <C> DecomposableMatchBuilder2<T, A, C> decomposeSecond(
      DecomposableMatchBuilder1<B, C> second) {
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            this.extractedIndexes.first(), this.extractedIndexes.second() + second.extractedIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the second element of
   * this match builder.
   *
   * <p>The second element is decomposed to 2 elements.
   */
  public <B1, B2> DecomposableMatchBuilder3<T, A, B1, B2> decomposeSecond(
      DecomposableMatchBuilder2<B, B1, B2> b) {
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            this.extractedIndexes.first(),
            this.extractedIndexes.second() + b.extractedIndexes.first(),
            this.extractedIndexes.second() + b.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 0
   * elements.
   */
  public DecomposableMatchBuilder0<T> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> first, DecomposableMatchBuilder0<B> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first, extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder0<>(matchers, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 0
   * elements.
   */
  public <A1> DecomposableMatchBuilder1<T, A1> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, A1> first, DecomposableMatchBuilder0<B> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first, extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.first() + first.extractedIndex, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 1
   * element.
   */
  public <B1> DecomposableMatchBuilder1<T, B1> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> first, DecomposableMatchBuilder1<B, B1> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first, extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.second() + second.extractedIndex, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 2 elements, and the second element is decomposed to 0
   * elements.
   */
  public <A1, A2> DecomposableMatchBuilder2<T, A1, A2> decomposeFirstAndSecond(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            extractedIndexes.first() + a.extractedIndexes.first(),
            extractedIndexes.first() + a.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 2
   * elements.
   */
  public <B1, B2> DecomposableMatchBuilder2<T, B1, B2> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            extractedIndexes.second() + b.extractedIndexes.first(),
            extractedIndexes.second() + b.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 1
   * element.
   */
  public <A1, B1> DecomposableMatchBuilder2<T, A1, B1> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            extractedIndexes.first() + a.extractedIndex,
            a.fieldMatchers.size() - 1 + extractedIndexes.second() + b.extractedIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 3 elements, and the second element is decomposed to 0
   * elements.
   */
  public <A1, A2, A3> DecomposableMatchBuilder3<T, A1, A2, A3> decomposeFirstAndSecond(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, DecomposableMatchBuilder0<B> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.first() + a.extractedIndexes.first(),
            extractedIndexes.first() + a.extractedIndexes.second(),
            extractedIndexes.first() + a.extractedIndexes.third());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 3
   * elements.
   */
  public <B1, B2, B3> DecomposableMatchBuilder3<T, B1, B2, B3> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder3<B, B1, B2, B3> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.second() + b.extractedIndexes.first(),
            extractedIndexes.second() + b.extractedIndexes.second(),
            extractedIndexes.second() + b.extractedIndexes.third());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 2 elements, and the second element is decomposed to 1
   * element.
   */
  public <A1, A2, B1> DecomposableMatchBuilder3<T, A1, A2, B1> decomposeFirstAndSecond(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder1<B, B1> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.first() + a.extractedIndexes.first(),
            extractedIndexes.first() + a.extractedIndexes.second(),
            a.fieldMatchers.size() - 1 + extractedIndexes.second() + b.extractedIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 2
   * elements.
   */
  public <A1, B1, B2> DecomposableMatchBuilder3<T, A1, B1, B2> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder2<B, B1, B2> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.first() + a.extractedIndex,
            a.fieldMatchers.size() - 1 + extractedIndexes.second() + b.extractedIndexes.first(),
            a.fieldMatchers.size() - 1 + extractedIndexes.second() + b.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
}
