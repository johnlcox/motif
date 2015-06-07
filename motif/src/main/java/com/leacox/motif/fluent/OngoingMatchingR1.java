package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor1;

import org.hamcrest.Matcher;

import java.util.function.Function;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR1<T, A, R> extends Matching1<T, A> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR1(
      FluentMatchingR<T, R> fluentMatchingR, Extractor1<T, A> extractor, Matcher<A> toMatchA) {
    super(extractor, toMatchA);

    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Function<A, R> function) {
    return get(fluentMatchingR, function);
  }
}
