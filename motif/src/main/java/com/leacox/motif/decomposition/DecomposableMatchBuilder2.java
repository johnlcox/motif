package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.util.Maps;

import com.leacox.motif.matchers.Matcher;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

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

  public <C> DecomposableMatchBuilder2<T, C, B> decomposeFirst(
      DecomposableMatchBuilder1<A, C> first) {
    Objects.requireNonNull(first);

    TreeMap<Integer, List<Matcher<Object>>> childMatchersByIndex = new TreeMap<>();
    childMatchersByIndex.put(0, first.fieldMatchers);

    List<Matcher<Object>> matchers =
        NestedMatchers.buildNestedMatchers(this.fieldMatchers, childMatchersByIndex);

    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(first.extractedIndex, this.extractedIndexes.second());

    TreeMap<Integer, FieldExtractor> childExtractorsByIndex = new TreeMap<>();
    childExtractorsByIndex.put(0, first.fieldExtractor);

    NestedFieldExtractor<T> nestedFieldExtractor =
        new NestedFieldExtractor<>(fieldExtractor, childExtractorsByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  public <C, D> DecomposableMatchBuilder2<T, C, D> decomposeFirstAndSecond(
      DecomposableMatchBuilder1<A, C> first, DecomposableMatchBuilder1<B, D> second) {
    Objects.requireNonNull(first);
    Objects.requireNonNull(second);

    Map<Integer, DecomposableMatchBuilder> buildersByIndex = Maps.treeMapOf(0, first, 1, second);

    List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);

    // The number of matchers in 'first' tells the number of elements before the start of the
    // decomposition of the 'second'.
    Tuple2<Integer, Integer> newIndexes =
        Tuple2.of(first.extractedIndex, first.fieldMatchers.size() + second.extractedIndex);

    TreeMap<Integer, FieldExtractor> childExtractorsByIndex =
        getChildExtractorsByIndex(buildersByIndex);

    NestedFieldExtractor<T> nestedFieldExtractor =
        new NestedFieldExtractor<>(fieldExtractor, childExtractorsByIndex);

    return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  }

  //public <C, D> DecomposableMatchBuilder2<T, C, D> decomposeFirstAndThird(
  //    DecomposableMatchBuilder1<A, C> first, DecomposableMatchBuilder1<B, D> second) {
  //  Objects.requireNonNull(first);
  //  Objects.requireNonNull(second);
  //
  //  Map<Integer, DecomposableMatchBuilder> buildersByIndex = Maps.treeMapOf(0, first, 1, second);
  //
  //  List<Matcher<Object>> matchers = getChildMatchers(buildersByIndex);
  //
  //  // The number of matchers in 'first' tells the number of elements before the start of the
  //  // decomposition of the 'second'.
  //  Tuple2<Integer, Integer> newIndexes =
  //      Tuple2.of(first.extractedIndex, first.fieldMatchers.size() + second.extractedIndex);
  //
  //  TreeMap<Integer, FieldExtractor> childExtractorsByIndex =
  //      getChildExtractorsByIndex(buildersByIndex);
  //
  //  NestedFieldExtractor<T> nestedFieldExtractor =
  //      new NestedFieldExtractor<>(fieldExtractor, childExtractorsByIndex);
  //
  //  return new DecomposableMatchBuilder2<>(matchers, newIndexes, nestedFieldExtractor);
  //}
}
