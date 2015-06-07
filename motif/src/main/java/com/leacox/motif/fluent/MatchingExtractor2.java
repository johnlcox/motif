package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor2;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class MatchingExtractor2<T, A, B> {
  final Extractor2<T, A, B> extractor;
  final Matcher<A> toMatchA;
  final Matcher<B> toMatchB;

  public MatchingExtractor2(
      Extractor2<T, A, B> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB) {
    this.extractor = extractor;
    this.toMatchA = toMatchA;
    this.toMatchB = toMatchB;
  }
}
