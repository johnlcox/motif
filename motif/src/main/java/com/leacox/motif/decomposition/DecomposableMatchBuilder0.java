package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.FieldExtractor;

import org.hamcrest.Matcher;

import java.util.List;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder0<T> {
  final List<Matcher<Object>> fieldMatchers;
  final FieldExtractor<T> fieldExtractor;

  public DecomposableMatchBuilder0(
      List<Matcher<Object>> fieldMatchers, FieldExtractor<T> fieldExtractor) {
    this.fieldMatchers = fieldMatchers;
    this.fieldExtractor = fieldExtractor;
  }

  public DecomposableMatch0<T> build() {
    return new DecomposableMatch0<>(fieldMatchers, fieldExtractor);
  }
}
