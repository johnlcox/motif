package com.leacox.motif;

import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;

/**
 * @author John Leacox
 */
public final class Motif {
  private Motif() {
  }

  public static <T> Matching<T> match(T value) {
    return new Matching<>(value);
  }

  public static final class Matching<T> {
    private final T value;

    public Matching(T value) {
      this.value = value;
    }

    @SafeVarargs
    public final <R> R on(Pattern<T, R>... patterns) {
      for (Pattern<T, R> pattern : patterns) {
        if (pattern.matches(value)) {
          return pattern.apply(value);
        }
      }

      throw new MatchException("No match found for " + value);
    }

    @SafeVarargs
    public final void on(ConsumablePattern<T>... patterns) {
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
}
