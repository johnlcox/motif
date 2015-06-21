package com.leacox.motif.extraction;

import com.leacox.motif.matchers.Matcher;

import java.util.List;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder0<T> extends DecomposableMatchBuilder<T> {
  public DecomposableMatchBuilder0(
      List<Matcher<Object>> fieldMatchers, FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);
  }

  public DecomposableMatch0<T> build() {
    return new DecomposableMatch0<>(fieldMatchers, fieldExtractor);
  }
}
