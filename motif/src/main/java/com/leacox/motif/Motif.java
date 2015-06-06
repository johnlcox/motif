package com.leacox.motif;

import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;

import java.util.Optional;

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

  public static class FluentMatching<T> {
    private final T value;

    public FluentMatching(T value) {
      this.value = value;
    }

    public <A> A when(Extractor1<T, A> extractor) {
      if (extractor.unapply(value).isPresent()) {
        return extractor.extract(value);
      } else {
        return null;
      }
    }
  }

  public static interface Extractor1<T, A> {
    public T apply(A a);

    public Optional<A> unapply(T t);

    public A extract(T t);
  }

  public static <T> Extractor1<Optional<T>, T> caseSome() {
    return new Extractor1<Optional<T>, T>() {

      @Override
      public Optional<T> apply(T t) {
        return Optional.ofNullable(t);
      }

      @Override
      public Optional<T> unapply(Optional<T> t) {
        if (t.isPresent()) {
          return t;
        } else {
          return Optional.empty();
        }
      }

      @Override
      public T extract(Optional<T> t) {
        return t.get();
      }
    };
  }
}
