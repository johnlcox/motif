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
 * A decomposable match builder that extracts 3 parameters.
 *
 * @author John Leacox
 */
public final class DecomposableMatchBuilder3<T, A, B, C> extends DecomposableMatchBuilder<T> {
  final Tuple3<Integer, Integer, Integer> extractedIndexes;

  /**
   * Creates a new instance of {@link DecomposableMatchBuilder3}.
   */
  public DecomposableMatchBuilder3(
      List<Matcher<Object>> fieldMatchers, Tuple3<Integer, Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);

    this.extractedIndexes = extractedIndexes;
  }

  private int fieldsToIndex(DecomposableMatchBuilder b) {
    return b.fieldMatchers.size() - 1;
  }

  /**
   * Builds a {@link DecomposableMatch3}.
   */
  public DecomposableMatch3<T, A, B, C> build() {
    return new DecomposableMatch3<>(fieldMatchers, extractedIndexes, fieldExtractor);
  }

  //region decomposeFirst

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 0 elements.
   */
  public DecomposableMatchBuilder2<T, B, C> decomposeFirst(DecomposableMatchBuilder0<A> a) {
    Objects.requireNonNull(a);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.second(),
        extractedIndexes.third());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first element of
   * this match builder.
   *
   * <p>The first element is decomposed to 1 element.
   */
  public <A1> DecomposableMatchBuilder3<T, A1, B, C> decomposeFirst(
      DecomposableMatchBuilder1<A, A1> a) {
    Objects.requireNonNull(a);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.second(),
        fieldsToIndex(a) + extractedIndexes.third());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
  //endregion

  //region decomposeSecond

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the second element of
   * this match builder.
   *
   * <p>The second element is decomposed to 0 elements.
   */
  public DecomposableMatchBuilder2<T, A, C> decomposeSecond(DecomposableMatchBuilder0<B> b) {
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            this.extractedIndexes.first(),
            this.extractedIndexes.third());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the second element of
   * this match builder.
   *
   * <p>The second element is decomposed to 1 element.
   */
  public <B1> DecomposableMatchBuilder3<T, A, B1, C> decomposeSecond(
      DecomposableMatchBuilder1<B, B1> b) {
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.first(),
            extractedIndexes.second() + b.extractedIndex,
            fieldsToIndex(b) + extractedIndexes.third());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
  //endregion

  //region decomposeThird

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the third element of
   * this match builder.
   *
   * <p>The third element is decomposed to 0 elements.
   */
  public DecomposableMatchBuilder2<T, A, B> decomposeThird(DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(extractedIndexes.first(), extractedIndexes.second());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the third element of
   * this match builder.
   *
   * <p>The third element is decomposed to 1 elements.
   */
  public <C1> DecomposableMatchBuilder3<T, A, B, C1> decomposeThird(
      DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            this.extractedIndexes.first(),
            this.extractedIndexes.second(),
            this.extractedIndexes.third() + c.extractedIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
  //endregion

  //region decomposeFirstAndSecond

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 0
   * elements.
   */
  public DecomposableMatchBuilder1<T, C> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.third(), nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 0
   * elements.
   */
  public <A1> DecomposableMatchBuilder2<T, A1, C> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.third());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 1
   * element.
   */
  public <B1> DecomposableMatchBuilder2<T, B1, C> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.second() + b.extractedIndex,
        fieldsToIndex(b) + extractedIndexes.third());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 2
   * elements.
   */
  public <B1, B2> DecomposableMatchBuilder3<T, B1, B2, C> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.second() + b.extractedIndexes.first(),
        extractedIndexes.second() + b.extractedIndexes.second(),
        fieldsToIndex(b) + extractedIndexes.third());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 1
   * element.
   */
  public <A1, B1> DecomposableMatchBuilder3<T, A1, B1, C> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.second() + b.extractedIndex,
        fieldsToIndex(a) + fieldsToIndex(b) + extractedIndexes.third());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first and second
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 2 elements, and the second element is decomposed to 0
   * elements.
   */
  public <A1, A2> DecomposableMatchBuilder3<T, A1, A2, C> decomposeFirstAndSecond(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.second(), b);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndexes.first(),
        extractedIndexes.first() + a.extractedIndexes.second(),
        fieldsToIndex(a) + extractedIndexes.third());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
  //endregion

  //region decomposeFirstAndThird

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first and third
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the third element is decomposed to 0
   * elements.
   */
  public DecomposableMatchBuilder1<T, B> decomposeFirstAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.second(), nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first and third
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the third element is decomposed to 1
   * element.
   */
  public <C1> DecomposableMatchBuilder2<T, B, C1> decomposeFirstAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.second(),
        extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first and third
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the third element is decomposed to 0
   * elements.
   */
  public <A1> DecomposableMatchBuilder2<T, A1, B> decomposeFirstAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.second());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first and third
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the third element is decomposed to 1
   * element.
   */
  public <A1, C1> DecomposableMatchBuilder3<T, A1, B, C1> decomposeFirstAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.second(),
        fieldsToIndex(a) + extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first and third
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the third element is decomposed to 2
   * elements.
   */
  public <C1, C2> DecomposableMatchBuilder3<T, B, C1, C2> decomposeFirstAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<C, C1, C2> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.second(),
        extractedIndexes.third() + c.extractedIndexes.first(),
        extractedIndexes.third() + c.extractedIndexes.second());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first and third
   * elements of this match builder.
   *
   * <p>The first element is decomposed to 2 elements, and the third element is decomposed to 0
   * elements.
   */
  public <A1, A2> DecomposableMatchBuilder3<T, A1, A2, B> decomposeFirstAndThird(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), a, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndexes.first(),
        extractedIndexes.first() + a.extractedIndexes.second(),
        fieldsToIndex(a) + extractedIndexes.second());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
  //endregion

  //region decomposeSecondAndThird

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the second and third
   * elements of this match builder.
   *
   * <p>The second element is decomposed to 0 elements, and the third element is decomposed to 0
   * elements.
   */
  public DecomposableMatchBuilder1<T, A> decomposeSecondAndThird(
      DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.first(), nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the second and third
   * elements of this match builder.
   *
   * <p>The second element is decomposed to 0 elements, and the third element is decomposed to 1
   * element.
   */
  public <C1> DecomposableMatchBuilder2<T, A, C1> decomposeSecondAndThird(
      DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(extractedIndexes.first(), extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the second and third
   * elements of this match builder.
   *
   * <p>The second element is decomposed to 0 elements, and the third element is decomposed to 2
   * elements.
   */
  public <C1, C2> DecomposableMatchBuilder3<T, A, C1, C2> decomposeSecondAndThird(
      DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder2<C, C1, C2> c) {
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.first(),
            extractedIndexes.third() + c.extractedIndexes.first(),
            extractedIndexes.third() + c.extractedIndexes.second());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the second and third
   * elements of this match builder.
   *
   * <p>The second element is decomposed to 1 element, and the third element is decomposed to 0
   * elements.
   */
  public <B1> DecomposableMatchBuilder2<T, A, B1> decomposeSecondAndThird(
      DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            extractedIndexes.first(),
            extractedIndexes.second() + b.extractedIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the second and third
   * elements of this match builder.
   *
   * <p>The second element is decomposed to 1 element, and the third element is decomposed to 1
   * element.
   */
  public <B1, C1> DecomposableMatchBuilder3<T, A, B1, C1> decomposeSecondAndThird(
      DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.first(),
            extractedIndexes.second() + b.extractedIndex,
            fieldsToIndex(b) + extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the second and third
   * elements of this match builder.
   *
   * <p>The second element is decomposed to 2 elements, and the third element is decomposed to 0
   * elements.
   */
  public <B1, B2> DecomposableMatchBuilder3<T, A, B1, B2> decomposeSecondAndThird(
      DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes =
        Tuple3.of(
            extractedIndexes.first(),
            extractedIndexes.second() + b.extractedIndexes.first(),
            extractedIndexes.second() + b.extractedIndexes.second());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
  //endregion

  //region decomposeFirstAndSecondAndThird

  /**
   * Returns a new {@link DecomposableMatchBuilder0} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 0 elements.
   */
  public DecomposableMatchBuilder0<T> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder0<>(matchers, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 1 element.
   */
  public <C1> DecomposableMatchBuilder1<T, C1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.third() + c.extractedIndex, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 2 elements.
   */
  public <C1, C2> DecomposableMatchBuilder2<T, C1, C2> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder2<C, C1, C2> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.third() + c.extractedIndexes.first(),
        extractedIndexes.third() + c.extractedIndexes.second());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 3 elements.
   */
  public <C1, C2, C3> DecomposableMatchBuilder3<T, C1, C2, C3> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder3<C, C1, C2, C3> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.third() + c.extractedIndexes.first(),
        extractedIndexes.third() + c.extractedIndexes.second(),
        extractedIndexes.third() + c.extractedIndexes.third());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 1
   * element, and the third element is decomposed to 0 elements.
   */
  public <B1> DecomposableMatchBuilder1<T, B1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.second() + b.extractedIndex, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 1
   * element, and the third element is decomposed to 1 element.
   */
  public <B1, C1> DecomposableMatchBuilder2<T, B1, C1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.second() + b.extractedIndex,
        fieldsToIndex(b) + extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 1
   * element, and the third element is decomposed to 2 elements.
   */
  public <B1, C1, C2> DecomposableMatchBuilder3<T, B1, C1, C2> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder2<C, C1, C2> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.second() + b.extractedIndex,
        fieldsToIndex(b) + extractedIndexes.third() + c.extractedIndexes.first(),
        fieldsToIndex(b) + extractedIndexes.third() + c.extractedIndexes.second());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 2
   * elements, and the third element is decomposed to 0 elements.
   */
  public <B1, B2> DecomposableMatchBuilder2<T, B1, B2> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.second() + b.extractedIndexes.first(),
        extractedIndexes.second() + b.extractedIndexes.second());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 2
   * elements, and the third element is decomposed to 1 element.
   */
  public <B1, B2, C1> DecomposableMatchBuilder3<T, B1, B2, C1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b,
      DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.second() + b.extractedIndexes.first(),
        extractedIndexes.second() + b.extractedIndexes.second(),
        fieldsToIndex(b) + extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 0 elements, and the second element is decomposed to 3
   * elements, and the third element is decomposed to 0 elements.
   */
  public <B1, B2, B3> DecomposableMatchBuilder3<T, B1, B2, B3> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder3<B, B1, B2, B3> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.second() + b.extractedIndexes.first(),
        extractedIndexes.second() + b.extractedIndexes.second(),
        extractedIndexes.second() + b.extractedIndexes.third());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder1} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 0 elements.
   */
  public <A1> DecomposableMatchBuilder1<T, A1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.first() + a.extractedIndex, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 1 element.
   */
  public <A1, C1> DecomposableMatchBuilder2<T, A1, C1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 2 elements.
   */
  public <A1, C1, C2> DecomposableMatchBuilder3<T, A1, C1, C2> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder2<C, C1, C2> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.third() + c.extractedIndexes.first(),
        fieldsToIndex(a) + extractedIndexes.third() + c.extractedIndexes.second());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 1
   * element, and the third element is decomposed to 0 elements.
   */
  public <A1, B1> DecomposableMatchBuilder2<T, A1, B1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.second() + b.extractedIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 1
   * element, and the third element is decomposed to 1 element.
   */
  public <A1, B1, C1> DecomposableMatchBuilder3<T, A1, B1, C1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.second() + b.extractedIndex,
        fieldsToIndex(a) + fieldsToIndex(b) + extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 1 element, and the second element is decomposed to 2
   * elements, and the third element is decomposed to 0 elements.
   */
  public <A1, B1, B2> DecomposableMatchBuilder3<T, A1, B1, B2> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder2<B, B1, B2> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndex,
        fieldsToIndex(a) + extractedIndexes.second() + b.extractedIndexes.first(),
        fieldsToIndex(a) + extractedIndexes.second() + b.extractedIndexes.second());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder2} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 2 elements, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 0 elements.
   */
  public <A1, A2> DecomposableMatchBuilder2<T, A1, A2> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes = Tuple2.of(
        extractedIndexes.first() + a.extractedIndexes.first(),
        extractedIndexes.first() + a.extractedIndexes.second());

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 2 elements, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 1 element.
   */
  public <A1, A2, C1> DecomposableMatchBuilder3<T, A1, A2, C1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder1<C, C1> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndexes.first(),
        extractedIndexes.first() + a.extractedIndexes.second(),
        fieldsToIndex(a) + extractedIndexes.third() + c.extractedIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 2 elements, and the second element is decomposed to 1
   * element, and the third element is decomposed to 0 elements.
   */
  public <A1, A2, B1> DecomposableMatchBuilder3<T, A1, A2, B1> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndexes.first(),
        extractedIndexes.first() + a.extractedIndexes.second(),
        fieldsToIndex(a) + extractedIndexes.second() + b.extractedIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

  /**
   * Returns a new {@link DecomposableMatchBuilder3} that further decomposes the first, second and
   * third elements of this match builder.
   *
   * <p>The first element is decomposed to 3 elements, and the second element is decomposed to 0
   * elements, and the third element is decomposed to 0 elements.
   */
  public <A1, A2, A3> DecomposableMatchBuilder3<T, A1, A2, A3> decomposeFirstAndSecondAndThird(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Objects.requireNonNull(c);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(
            extractedIndexes.first(), a, extractedIndexes.second(), b, extractedIndexes.third(), c);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    Tuple3<Integer, Integer, Integer> newIndexes = Tuple3.of(
        extractedIndexes.first() + a.extractedIndexes.first(),
        extractedIndexes.first() + a.extractedIndexes.second(),
        extractedIndexes.first() + a.extractedIndexes.third());

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
  //endregion
}
