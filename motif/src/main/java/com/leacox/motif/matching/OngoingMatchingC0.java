package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor0;
import com.leacox.motif.function.Consumer0;

/**
 * @author John Leacox
 */
public final class OngoingMatchingC0<T, U extends T> extends Matching0<T, U> {
  private final FluentMatchingC<T> fluentMatchingC;

  OngoingMatchingC0(FluentMatchingC<T> fluentMatchingC, Extractor0<U> extractor) {
    super(extractor);

    this.fluentMatchingC = fluentMatchingC;
  }

  public FluentMatchingC<T> then(Consumer0 consumer) {
    return then(fluentMatchingC, consumer);
  }
}
