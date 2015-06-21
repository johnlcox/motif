package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor0;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR0<T, U extends T, R> extends Matching0<T, U> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR0(FluentMatchingR<T, R> fluentMatchingR, Extractor0<U> extractor) {
    super(extractor);
    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Supplier<R> supplier) {
    return get(fluentMatchingR, supplier);
  }
}
