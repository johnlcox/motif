package com.leacox.motif;

import com.leacox.motif.pattern.IntPattern;

/**
 * @author John Leacox
 */
public class YesIntMatch<R> implements IntMatch<R> {
  private final R result;

  public YesIntMatch(R result) {
    this.result = result;
  }

  @Override
  public IntMatch<R> when(IntPattern<R> pattern) {
    return this;
  }

  //@Override
  //public MatchEnd<R> orElse(R other) {
  //  return new MatchEnd<>(result);
  //}
  //
  //@Override
  //public MatchEnd<R> orElse(Supplier<R> supplier) {
  //  return new MatchEnd<>(result);
  //}

  @Override
  public R get() {
    return result;
  }
}
