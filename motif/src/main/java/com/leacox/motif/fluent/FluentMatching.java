package com.leacox.motif.fluent;

/**
 * @author John Leacox
 */
public class FluentMatching<T> {
  final T value;

  FluentMatching(T value) {
    this.value = value;
  }

  public <A> PatternBuilder1<T, A> when(MatchBuilder1<T, A> matchBuilder) {
    return new PatternBuilder1<>(matchBuilder.extractor, value, matchBuilder.toMatch);
  }

  public <A> PatternBuilder0<T, A> when(MatchBuilder0<T, A> matchBuilder) {
    return new PatternBuilder0<>(matchBuilder.extractor, value);
  }
}
