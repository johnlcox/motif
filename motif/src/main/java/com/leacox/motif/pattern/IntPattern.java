package com.leacox.motif.pattern;

import java.util.function.IntFunction;

/**
 * @author John Leacox
 */
public interface IntPattern<R> {
  boolean matches(int value);

  R apply(int value);

  static <R> IntPattern<R> of(IntFunction<Boolean> matcher, IntFunction<R> function) {
    return new IntPattern<R>() {
      @Override
      public boolean matches(int value) {
        return matcher.apply(value);
      }

      @Override
      public R apply(int value) {
        return function.apply(value);
      }
    };
  }
}
