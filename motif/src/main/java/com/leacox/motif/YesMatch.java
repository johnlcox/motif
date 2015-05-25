package com.leacox.motif;

import com.leacox.motif.pattern.Pattern;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
class YesMatch<T, R> implements Match<T, R> {
  private final R result;

  public YesMatch(R result) {
    this.result = result;
  }

  @Override
  public Match<T, R> when(Pattern<T, R> pattern) {
    return this;
  }

  @Override
  public MatchEnd<R> orElse(R other) {
    return new MatchEnd<>(result);
  }

  @Override
  public MatchEnd<R> orElse(Supplier<R> supplier) {
    return new MatchEnd<>(result);
  }

  @Override
  public R get() {
    return result;
  }
}
