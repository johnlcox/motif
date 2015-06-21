package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor3;

import com.leacox.motif.matchers.Matcher;

/**
 * @author John Leacox
 */
public final class MatchingExtractor3<T, A, B, C> {
  final Extractor3<T, A, B, C> extractor;
  final Matcher<A> toMatchA;
  final Matcher<B> toMatchB;
  final Matcher<C> toMatchC;

  private MatchingExtractor3(
      Extractor3<T, A, B, C> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB,
      Matcher<C> toMatchC) {
    this.extractor = extractor;
    this.toMatchA = toMatchA;
    this.toMatchB = toMatchB;
    this.toMatchC = toMatchC;
  }

  public static <T, A, B, C> MatchingExtractor3<T, A, B, C> create(
      Extractor3<T, A, B, C> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB,
      Matcher<C> toMatchC) {
    return new MatchingExtractor3<>(extractor, toMatchA, toMatchB, toMatchC);
  }
}
