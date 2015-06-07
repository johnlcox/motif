package com.leacox.motif.fluent;

/**
 * @author John Leacox
 */
public class FluentMatching<T> {
  final T value;

  FluentMatching(T value) {
    this.value = value;
  }

  public <U> InitialMatching0<T, U> when(MatchingExtractor0<U> matchingExtractor) {
    return new InitialMatching0<>(matchingExtractor.extractor, value);
  }

  public <U, A> InitialMatching1<T, U, A> when(MatchingExtractor1<U, A> matchingExtractor) {
    return new InitialMatching1<>(matchingExtractor.extractor, value, matchingExtractor.toMatch);
  }

  public <U, A, B> InitialMatching2<T, U, A, B> when(
      MatchingExtractor2<U, A, B> matchingExtractor) {
    return new InitialMatching2<>(
        matchingExtractor.extractor, value, matchingExtractor.toMatchA, matchingExtractor.toMatchB);
  }

  public <U, A, B, C> InitialMatching3<T, U, A, B, C> when(
      MatchingExtractor3<U, A, B, C> matchingExtractor) {
    return new InitialMatching3<>(
        matchingExtractor.extractor, value, matchingExtractor.toMatchA, matchingExtractor.toMatchB,
        matchingExtractor.toMatchC);
  }
}
