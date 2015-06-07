package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor2;
import com.leacox.motif.function.Function2;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR2<T, A, B, R> extends Matching2<T, A, B> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR2(
      FluentMatchingR<T, R> fluentMatchingR, Extractor2<T, A, B> extractor, Matcher<A> toMatchA,
      Matcher<B> toMatchB) {
    super(extractor, toMatchA, toMatchB);

    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Function2<A, B, R> function) {
    return get(fluentMatchingR, function);
  }
}
