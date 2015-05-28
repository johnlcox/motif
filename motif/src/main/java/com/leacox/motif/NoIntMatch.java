package com.leacox.motif;

import com.leacox.motif.pattern.IntPattern;

/**
 * @author John Leacox
 */
public class NoIntMatch<R> implements IntMatch<R> {
  private final int value;

  public NoIntMatch(int value) {
    this.value = value;
  }

  @Override
  public IntMatch<R> when(IntPattern<R> pattern) {
    return pattern.matches(value) ? new YesIntMatch<>(pattern.apply(value)) : this;
  }

  @Override
  public R get() {
    throw new MatchException("No match found for " + value);
  }
}
