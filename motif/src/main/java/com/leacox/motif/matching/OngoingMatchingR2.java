package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor2;
import com.leacox.motif.function.Function2;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR2<T, U extends T, A, B, R> extends Matching2<T, U, A, B> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR2(FluentMatchingR<T, R> fluentMatchingR, Extractor2<U, A, B> extractor) {
    super(extractor);

    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Function2<A, B, R> function) {
    return get(fluentMatchingR, function);
  }
}
