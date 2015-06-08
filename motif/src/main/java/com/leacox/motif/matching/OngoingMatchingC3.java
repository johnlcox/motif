package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor3;
import com.leacox.motif.function.Consumer3;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public class OngoingMatchingC3<T, U extends T, A, B, C> extends Matching3<T, U, A, B, C> {
  private final FluentMatchingC<T> fluentMatchingC;

  OngoingMatchingC3(
      FluentMatchingC<T> fluentMatchingC, Extractor3<U, A, B, C> extractor, Matcher<A> toMatchA,
      Matcher<B> toMatchB, Matcher<C> toMatchC) {
    super(extractor, toMatchA, toMatchB, toMatchC);

    this.fluentMatchingC = fluentMatchingC;
  }

  public FluentMatchingC<T> then(Consumer3<A, B, C> consumer) {
    return then(fluentMatchingC, consumer);
  }
}
