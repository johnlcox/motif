package com.leacox.motif;

import com.leacox.motif.pattern.Pattern;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
class NoMatch<T, R> implements Match<T, R> {
  private final T value;

  public NoMatch(T value) {
    this.value = value;
  }

  @Override
  public Match<T, R> when(Pattern<T, R> pattern) {
    return pattern.matches(value) ? new YesMatch<>(pattern.apply(value)) : this;
  }

  @Override
  public MatchEnd<R> orElse(R result) {
    return new MatchEnd<>(result);
  }

  @Override
  public MatchEnd<R> orElse(Supplier<R> supplier) {
    return new MatchEnd<>(supplier.get());
  }

  @Override
  public R get() {
    throw new MatchException("No match found for " + value);
  }
}
