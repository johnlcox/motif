package com.leacox.motif.extraction;

import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;
import com.leacox.motif.util.Maps;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder2<T, A, B> extends DecomposableMatchBuilder<T> {
  final Tuple2<Integer, Integer> extractedIndexes;

  public DecomposableMatchBuilder2(
      List<Matcher<Object>> fieldMatchers, Tuple2<Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);

    this.extractedIndexes = extractedIndexes;
  }

  public DecomposableMatch2<T, A, B> build() {
    return new DecomposableMatch2<>(fieldMatchers, extractedIndexes, fieldExtractor);
  }

  // Decomposes the first extracted value and drops it down to a match on only the second value.
  public DecomposableMatchBuilder1<T, B> decomposeFirst(DecomposableMatchBuilder0<A> first) {
    Objects.requireNonNull(first);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.second(), nestedFieldExtractor);
  }

  public <A1> DecomposableMatchBuilder2<T, A1, B> decomposeFirst(
      DecomposableMatchBuilder1<A, A1> first) {
    Objects.requireNonNull(first);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            this.extractedIndexes.first() + first.extractedIndex, this.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

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

  // Decomposes the second extracted value and drops it down to a match on only the first value.
  public DecomposableMatchBuilder1<T, A> decomposeSecond(DecomposableMatchBuilder0<B> second) {
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, extractedIndexes.first(), nestedFieldExtractor);
  }

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

  public <A1> DecomposableMatchBuilder1<T, A1> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, A1> first, DecomposableMatchBuilder0<B> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first, extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(matchers, first.extractedIndex, nestedFieldExtractor);
  }

  public <B1> DecomposableMatchBuilder1<T, B1> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> first, DecomposableMatchBuilder1<B, B1> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first, extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, 1 + second.extractedIndex, nestedFieldExtractor);
  }

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
            extractedIndexes.first() + /*a.fieldMatchers.size() +*/ a.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

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
            extractedIndexes.second() + /*a.fieldMatchers.size() +*/ b.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  public <A1, B1> DecomposableMatchBuilder2<T, A1, B1> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, A1> first, DecomposableMatchBuilder1<B, B1> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first, extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(
            extractedIndexes.first() + first.extractedIndex,
            extractedIndexes.first() + first.fieldMatchers.size() + second.extractedIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

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
            extractedIndexes.first() + a.fieldMatchers.size() + b.extractedIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }

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
            extractedIndexes.first() + a.fieldMatchers.size() + b.extractedIndexes.first(),
            extractedIndexes.first() + a.fieldMatchers.size() + b.extractedIndexes.second());

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder3<>(matchers, newIndexes, nestedFieldExtractor);
  }
}
