package com.leacox.motif.fluent;

/**
 * @author John Leacox
 */
public class FluentMatching<T> {
  final T value;

  FluentMatching(T value) {
    this.value = value;
  }

  public <U> InitialMatching0<T, U> when(MatchingExtractor0<U> matchBuilder) {
    return new InitialMatching0<>(matchBuilder.extractor, value);
  }

  public <U, A> InitialMatching1<T, U, A> when(MatchingExtractor1<U, A> matchBuilder) {
    return new InitialMatching1<>(matchBuilder.extractor, value, matchBuilder.toMatch);
  }

  public <U, A, B> InitialMatching2<T, U, A, B> when(MatchingExtractor2<U, A, B> matchBuilder) {
    return new InitialMatching2<>(
        matchBuilder.extractor, value, matchBuilder.toMatchA, matchBuilder.toMatchB);
  }

  public <U, A, B, C> InitialMatching3<T, U, A, B, C> when(
      MatchingExtractor3<U, A, B, C> matchBuilder) {
    return new InitialMatching3<>(
        matchBuilder.extractor, value, matchBuilder.toMatchA, matchBuilder.toMatchB,
        matchBuilder.toMatchC);
  }
}
