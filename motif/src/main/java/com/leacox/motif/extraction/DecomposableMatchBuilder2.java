package com.leacox.motif.extraction;

import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;
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

  public <C> DecomposableMatchBuilder2<T, C, B> decomposeFirst(
      DecomposableMatchBuilder1<A, C> first) {
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

  public <C, D> DecomposableMatchBuilder0<T> decomposeFirstAndSecond(
      DecomposableMatchBuilder0<A> first, DecomposableMatchBuilder0<B> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex =
        Maps.treeMapOf(extractedIndexes.first(), first, extractedIndexes.second(), second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor = getNestedFieldExtractor(buildersByIndex);

    return new DecomposableMatchBuilder0<>(matchers, nestedFieldExtractor);
  }

  public <C, D> DecomposableMatchBuilder2<T, C, D> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, C> first, DecomposableMatchBuilder1<B, D> second) {
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
}
