package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor2;
import com.leacox.motif.function.Consumer2;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class OngoingMatchingC2<T, A, B> extends Matching2<T, A, B> {
  private final FluentMatchingC<T> fluentMatchingC;

  OngoingMatchingC2(
      FluentMatchingC<T> fluentMatchingC, Extractor2<T, A, B> extractor, Matcher<A> toMatchA,
      Matcher<B> toMatchB) {
    super(extractor, toMatchA, toMatchB);

    this.fluentMatchingC = fluentMatchingC;
  }

  public FluentMatchingC<T> then(Consumer2<A, B> consumer) {
    return then(fluentMatchingC, consumer);
  }
}
