package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor3;
import com.leacox.motif.function.Function3;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR3<T, U extends T, A, B, C, R> extends Matching3<T, U, A, B, C> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR3(FluentMatchingR<T, R> fluentMatchingR, Extractor3<U, A, B, C> extractor) {
    super(extractor);

    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Function3<A, B, C, R> function) {
    return get(fluentMatchingR, function);
  }
}
