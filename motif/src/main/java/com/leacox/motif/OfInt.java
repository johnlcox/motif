package com.leacox.motif;

import com.leacox.motif.pattern.IntPattern;

/**
 * @author John Leacox
 */
public final class OfInt {
  private final int value;

  OfInt(int value) {
    this.value = value;
  }

  public <R> IntMatch<R> when(IntPattern<R> pattern) {
    return pattern.matches(value) ? new YesIntMatch<>(pattern.apply(value))
        : new NoIntMatch<>(value);
  }
}
