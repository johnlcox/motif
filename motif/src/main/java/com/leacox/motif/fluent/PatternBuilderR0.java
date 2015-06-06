package com.leacox.motif.fluent;

import com.leacox.motif.Extractor0;
import com.leacox.motif.pattern.Pattern;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public class PatternBuilderR0<T, A, R> {
  private final FluentMatchingR<T, A, R> fluentMatchingR;
  private final Extractor0<T> extractor;

  PatternBuilderR0(
      FluentMatchingR<T, A, R> fluentMatchingR, Extractor0<T> extractor) {
    this.fluentMatchingR = fluentMatchingR;
    this.extractor = extractor;
  }

  public FluentMatchingR<T, A, R> is(Supplier<R> supplier) {
    fluentMatchingR.addPattern(
        Pattern
            .<T, R>of(extractor::unapply, t -> supplier.get()));

    return fluentMatchingR;
  }
}
