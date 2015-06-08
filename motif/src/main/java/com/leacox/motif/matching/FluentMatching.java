package com.leacox.motif.matching;

/**
 * @author John Leacox
 */
public final class FluentMatching<T> {
  final T value;

  public FluentMatching(T value) {
    this.value = value;
  }

  public <U extends T> InitialMatching0<T, U> when(MatchingExtractor0<U> matchingExtractor) {
    return new InitialMatching0<>(matchingExtractor.extractor, value);
  }

  public <U extends T, A> InitialMatching1<T, U, A> when(
      MatchingExtractor1<U, A> matchingExtractor) {
    return new InitialMatching1<>(matchingExtractor.extractor, value, matchingExtractor.toMatch);
  }

  public <U extends T, A, B> InitialMatching2<T, U, A, B> when(
      MatchingExtractor2<U, A, B> matchingExtractor) {
    return new InitialMatching2<>(
        matchingExtractor.extractor, value, matchingExtractor.toMatchA, matchingExtractor.toMatchB);
  }

  public <U extends T, A, B, C> InitialMatching3<T, U, A, B, C> when(
      MatchingExtractor3<U, A, B, C> matchingExtractor) {
    return new InitialMatching3<>(
        matchingExtractor.extractor, value, matchingExtractor.toMatchA, matchingExtractor.toMatchB,
        matchingExtractor.toMatchC);
  }
}
