package com.leacox.motif;

/**
 * @author John Leacox
 */
public final class MatchEnd<R> {
  private final R result;

  MatchEnd(R result) {
    this.result = result;
  }

  public R get() {
    return result;
  }
}
