package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor1;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class MatchingExtractor1<T, A> {
  final Extractor1<T, A> extractor;
  final Matcher<A> toMatch;

  private MatchingExtractor1(Extractor1<T, A> extractor, Matcher<A> toMatch) {
    this.extractor = extractor;
    this.toMatch = toMatch;
  }

  public static <T, A> MatchingExtractor1<T, A> create(
      Extractor1<T, A> extractor, Matcher<A> toMatch) {
    return new MatchingExtractor1<>(extractor, toMatch);
  }
}
