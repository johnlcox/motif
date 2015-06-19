package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.FieldExtractor;
import com.leacox.motif.tuple.Tuple3;

import org.hamcrest.Matcher;

import java.util.List;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder3<T, A, B, C> {
  final List<Matcher<Object>> fieldMatchers;
  final Tuple3<Integer, Integer, Integer> extractedIndexes;
  final FieldExtractor<T> fieldExtractor;

  public DecomposableMatchBuilder3(
      List<Matcher<Object>> fieldMatchers, Tuple3<Integer, Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    this.fieldMatchers = fieldMatchers;
    this.extractedIndexes = extractedIndexes;
    this.fieldExtractor = fieldExtractor;
  }

  public DecomposableMatch3<T, A, B, C> build() {
    return new DecomposableMatch3<>(fieldMatchers, extractedIndexes, fieldExtractor);
  }
}
