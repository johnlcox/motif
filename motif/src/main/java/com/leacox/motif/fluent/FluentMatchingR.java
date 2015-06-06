package com.leacox.motif.fluent;

import com.leacox.motif.MatchException;
import com.leacox.motif.pattern.Pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Leacox
 */
public class FluentMatchingR<T, A, R> {
  final T value;
  private final List<Pattern<T, R>> patterns = new ArrayList<>();

  FluentMatchingR(T value) {
    this.value = value;
  }

  public void addPattern(Pattern<T, R> pattern) {
    patterns.add(pattern);
  }

  public PatternBuilderR1<T, A, R> when(MatchBuilder1<T, A> matchBuilder) {
    return new PatternBuilderR1<>(this, matchBuilder.extractor, matchBuilder.toMatch);
  }

  public PatternBuilderR0<T, A, R> when(MatchBuilder0<T, A> matchBuilder) {
    return new PatternBuilderR0<>(this, matchBuilder.extractor);
  }

  public R getMatch() {
    for (Pattern<T, R> pattern : patterns) {
      if (pattern.matches(value)) {
        return pattern.apply(value);
      }
    }

    throw new MatchException("No match found for " + value);
  }
}
