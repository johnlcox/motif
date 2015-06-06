package com.leacox.motif.fluent;

import com.leacox.motif.MatchException;
import com.leacox.motif.pattern.ConsumablePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Leacox
 */
public class FluentMatchingC<T, A> {
  final T value;
  private final List<ConsumablePattern<T>> patterns = new ArrayList<>();

  FluentMatchingC(T value) {
    this.value = value;
  }

  public void addPattern(ConsumablePattern<T> pattern) {
    patterns.add(pattern);
  }

  public PatternBuilder1<T, A> when(MatchBuilder1<T, A> matchBuilder) {
    return new PatternBuilder1<>(matchBuilder.extractor, value, matchBuilder.toMatch);
  }

  public PatternBuilder0<T, A> when(MatchBuilder0<T, A> matchBuilder) {
    return new PatternBuilder0<>(matchBuilder.extractor, value);
  }

  public void doMatch() {
    boolean matchFound = false;
    for (ConsumablePattern<T> pattern : patterns) {
      if (pattern.matches(value)) {
        pattern.consume(value);
        matchFound = true;
        break;
      }
    }

    if (!matchFound) {
      throw new MatchException("No match found for " + value);
    }
  }
}
