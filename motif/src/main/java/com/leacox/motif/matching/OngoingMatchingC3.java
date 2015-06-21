package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor3;
import com.leacox.motif.function.Consumer3;

/**
 * @author John Leacox
 */
public class OngoingMatchingC3<T, U extends T, A, B, C> extends Matching3<T, U, A, B, C> {
  private final FluentMatchingC<T> fluentMatchingC;

  OngoingMatchingC3(FluentMatchingC<T> fluentMatchingC, Extractor3<U, A, B, C> extractor) {
    super(extractor);

    this.fluentMatchingC = fluentMatchingC;
  }

  public FluentMatchingC<T> then(Consumer3<A, B, C> consumer) {
    return then(fluentMatchingC, consumer);
  }
}
