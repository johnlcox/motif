package com.leacox.motif.fluent;

import com.leacox.motif.Extractor1;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public class MatchBuilder1<T, S> {
  final Extractor1<T, S> extractor;
  final Matcher<S> toMatch;

  public MatchBuilder1(Extractor1<T, S> extractor, Matcher<S> toMatch) {
    this.extractor = extractor;
    this.toMatch = toMatch;
  }

  //public PatternBuilder1<T, S> with(FluentMatching<T> fluentMatching) {
  //  return new PatternBuilder1<>(fluentMatching, extractor, toMatch);
  //}
}
