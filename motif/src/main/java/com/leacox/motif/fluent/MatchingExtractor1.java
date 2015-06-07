package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor1;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class MatchingExtractor1<T, A> {
  final Extractor1<T, A> extractor;
  final Matcher<A> toMatch;

  public MatchingExtractor1(Extractor1<T, A> extractor, Matcher<A> toMatch) {
    this.extractor = extractor;
    this.toMatch = toMatch;
  }
}
