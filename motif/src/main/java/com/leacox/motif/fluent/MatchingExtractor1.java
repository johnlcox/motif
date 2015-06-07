package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor1;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class MatchingExtractor1<T, S> {
  final Extractor1<T, S> extractor;
  final Matcher<S> toMatch;

  public MatchingExtractor1(Extractor1<T, S> extractor, Matcher<S> toMatch) {
    this.extractor = extractor;
    this.toMatch = toMatch;
  }
}
