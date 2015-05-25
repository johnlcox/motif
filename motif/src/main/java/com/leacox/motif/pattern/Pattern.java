package com.leacox.motif.pattern;

import java.util.function.Function;

/**
 * @author John Leacox
 */
public interface Pattern<T, R> {
  boolean matches(T value);

  R apply(T value);

  static <T, R> Pattern<T, R> of(Function<T, Boolean> matcher, Function<T, R> function) {
    return new Pattern<T, R>() {
      @Override
      public boolean matches(T value) {
        return matcher.apply(value);
      }

      @Override
      public R apply(T value) {
        return function.apply(value);
      }
    };
  }
}
