package com.leacox.motif.fluent;

/**
 * @author John Leacox
 */
public class FluentMatching<T> {
  final T value;

  FluentMatching(T value) {
    this.value = value;
  }

  public InitialMatching0<T> when(MatchingExtractor0<T> matchBuilder) {
    return new InitialMatching0<>(matchBuilder.extractor, value);
  }

  public <A> InitialMatching1<T, A> when(MatchingExtractor1<T, A> matchBuilder) {
    return new InitialMatching1<>(matchBuilder.extractor, value, matchBuilder.toMatch);
  }

  public <A, B> InitialMatching2<T, A, B> when(MatchingExtractor2<T, A, B> matchBuilder) {
    return new InitialMatching2<>(
        matchBuilder.extractor, value, matchBuilder.toMatchA, matchBuilder.toMatchB);
  }
}
