package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor1;

import org.hamcrest.Matcher;

import java.util.function.Consumer;

/**
 * @author John Leacox
 */
public final class OngoingMatchingC1<T, U, A> extends Matching1<T, U, A> {
  private final FluentMatchingC<T> fluentMatchingC;

  OngoingMatchingC1(
      FluentMatchingC<T> fluentMatchingC, Extractor1<U, A> extractor, Matcher<A> toMatchA) {
    super(extractor, toMatchA);

    this.fluentMatchingC = fluentMatchingC;
  }

  public FluentMatchingC<T> then(Consumer<A> consumer) {
    return then(fluentMatchingC, consumer);
  }
}
