package com.leacox.motif.pattern;

import java.util.function.Function;

/**
 * @author John Leacox
 */
public class BasePattern<T, R> implements Pattern<T, R> {
  private final Function<T, Boolean> matcher;
  private final Function<T, R> function;

  public BasePattern(Function<T, Boolean> matcher, Function<T, R> function) {
    this.matcher = matcher;
    this.function = function;
  }

  @Override
  public boolean matches(T value) {
    return matcher.apply(value);
  }

  @Override
  public R apply(T value) {
    return function.apply(value);
  }
}
