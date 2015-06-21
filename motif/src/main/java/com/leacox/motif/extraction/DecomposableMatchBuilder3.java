package com.leacox.motif.extraction;

import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple3;

import java.util.List;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder3<T, A, B, C> extends DecomposableMatchBuilder<T> {
  final Tuple3<Integer, Integer, Integer> extractedIndexes;

  public DecomposableMatchBuilder3(
      List<Matcher<Object>> fieldMatchers, Tuple3<Integer, Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);

    this.extractedIndexes = extractedIndexes;
  }

  public DecomposableMatch3<T, A, B, C> build() {
    return new DecomposableMatch3<>(fieldMatchers, extractedIndexes, fieldExtractor);
  }
}
