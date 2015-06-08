package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor2;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class MatchingExtractor2<T, A, B> {
  final Extractor2<T, A, B> extractor;
  final Matcher<A> toMatchA;
  final Matcher<B> toMatchB;

  MatchingExtractor2(
      Extractor2<T, A, B> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB) {
    this.extractor = extractor;
    this.toMatchA = toMatchA;
    this.toMatchB = toMatchB;
  }

  public static <T, A, B> MatchingExtractor2<T, A, B> create(
      Extractor2<T, A, B> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB) {
    return new MatchingExtractor2<>(extractor, toMatchA, toMatchB);
  }
}
