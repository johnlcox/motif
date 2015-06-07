package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor3;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class MatchingExtractor3<T, A, B, C> {
  final Extractor3<T, A, B, C> extractor;
  final Matcher<A> toMatchA;
  final Matcher<B> toMatchB;
  final Matcher<C> toMatchC;

  public MatchingExtractor3(
      Extractor3<T, A, B, C> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB,
      Matcher<C> toMatchC) {
    this.extractor = extractor;
    this.toMatchA = toMatchA;
    this.toMatchB = toMatchB;
    this.toMatchC = toMatchC;
  }
}
