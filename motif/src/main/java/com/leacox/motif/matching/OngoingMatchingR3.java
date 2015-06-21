package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor3;
import com.leacox.motif.function.Function3;

import com.leacox.motif.matchers.Matcher;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR3<T, U extends T, A, B, C, R> extends Matching3<T, U, A, B, C> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR3(
      FluentMatchingR<T, R> fluentMatchingR, Extractor3<U, A, B, C> extractor, Matcher<A> toMatchA,
      Matcher<B> toMatchB, Matcher<C> toMatchC) {
    super(extractor, toMatchA, toMatchB, toMatchC);

    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Function3<A, B, C, R> function) {
    return get(fluentMatchingR, function);
  }
}
