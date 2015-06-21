package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;

import com.leacox.motif.matchers.Matcher;

import java.util.List;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder1<T, A> extends DecomposableMatchBuilder<T> {
  final List<Matcher<Object>> fieldMatchers;
  final int extractedIndex;
  final FieldExtractor<T> fieldExtractor;

  public DecomposableMatchBuilder1(
      List<Matcher<Object>> fieldMatchers, int extractedIndex,
      FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);

    this.fieldMatchers = fieldMatchers;
    this.extractedIndex = extractedIndex;
    this.fieldExtractor = fieldExtractor;
  }

  public DecomposableMatch1<T, A> build() {
    return new DecomposableMatch1<>(fieldMatchers, extractedIndex, fieldExtractor);
  }

  public <B> DecomposableMatchBuilder1<T, B> decomposeFirst(
      DecomposableMatchBuilder1<A, B> first) {
    return decomposeOneForIndex(first, extractedIndex);
  }

  public <B, C> DecomposableMatchBuilder2<T, B, C> decomposeFirst(
      DecomposableMatchBuilder2<A, B, C> first) {
    return decomposeOneForIndex(first, extractedIndex);
  }

  private <B> DecomposableMatchBuilder1<T, B> decomposeOneForIndex(
      DecomposableMatchBuilder1<A, B> child, int index) {
    Objects.requireNonNull(child);

    TreeMap<Integer, List<Matcher<Object>>> childMatchersByIndex = new TreeMap<>();
    childMatchersByIndex.put(index, child.fieldMatchers);

    List<Matcher<Object>> matchers =
        NestedMatchers.buildNestedMatchers(this.fieldMatchers, childMatchersByIndex);

    TreeMap<Integer, FieldExtractor> childExtractorsByIndex = new TreeMap<>();
    childExtractorsByIndex.put(index, child.fieldExtractor);

    NestedFieldExtractor<T> nestedFieldExtractor =
        new NestedFieldExtractor<>(fieldExtractor, childExtractorsByIndex);

    return new DecomposableMatchBuilder1<>(
        matchers, index + child.extractedIndex, nestedFieldExtractor);
  }

  private <B, C> DecomposableMatchBuilder2<T, B, C> decomposeOneForIndex(
      DecomposableMatchBuilder2<A, B, C> child, int index) {
    Objects.requireNonNull(child);

    TreeMap<Integer, List<Matcher<Object>>> childMatchersByIndex = new TreeMap<>();
    childMatchersByIndex.put(index, child.fieldMatchers);

    List<Matcher<Object>> matchers =
        NestedMatchers.buildNestedMatchers(this.fieldMatchers, childMatchersByIndex);

    TreeMap<Integer, FieldExtractor> childExtractorsByIndex = new TreeMap<>();
    childExtractorsByIndex.put(index, child.fieldExtractor);

    NestedFieldExtractor<T> nestedFieldExtractor =
        new NestedFieldExtractor<>(fieldExtractor, childExtractorsByIndex);

    Tuple2<Integer, Integer> extractedIndexs =
        Tuple2.of(index + child.extractedIndexes.first(), index + child.extractedIndexes.second());

    return new DecomposableMatchBuilder2<>(
        matchers, extractedIndexs, nestedFieldExtractor);
  }
}
