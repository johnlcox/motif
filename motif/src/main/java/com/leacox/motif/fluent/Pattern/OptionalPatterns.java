package com.leacox.motif.fluent.Pattern;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.Extractor0;
import com.leacox.motif.Extractor1;
import com.leacox.motif.fluent.MatchBuilder0;
import com.leacox.motif.fluent.MatchBuilder1;
import com.leacox.motif.matchers.ArgumentMatcher;

import java.util.Optional;

/**
 * @author John Leacox
 */
public final class OptionalPatterns {
  private OptionalPatterns() {
  }

  public static <T> MatchBuilder1<Optional<T>, T> caseSome2(T a) {
    return caseSome2(eq(a));
  }

  public static <T> MatchBuilder1<Optional<T>, T> caseSome2(ArgumentMatcher<T> a) {
    Extractor1<Optional<T>, T> extractor = new Extractor1<Optional<T>, T>() {
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
    };

    return new MatchBuilder1<>(extractor, a);
  }

  public static <T> MatchBuilder0<Optional<T>, T> caseNone2() {
    Extractor0<Optional<T>> extractor = new Extractor0<Optional<T>>() {
      @Override
      public Optional<T> apply() {
        return Optional.empty();
      }

      @Override
      public boolean unapply(Optional<T> t) {
        return !t.isPresent();
      }
    };

    return new MatchBuilder0<>(extractor);
  }
}
