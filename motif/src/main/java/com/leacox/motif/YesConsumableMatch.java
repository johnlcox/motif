package com.leacox.motif;

import com.leacox.motif.pattern.ConsumablePattern;

import java.util.function.Consumer;

/**
 * @author John Leacox
 */
class YesConsumableMatch<T> implements ConsumableMatch<T> {
  public YesConsumableMatch() {
  }

  @Override
  public ConsumableMatch<T> when(ConsumablePattern<T> pattern) {
    return this;
  }

  @Override
  public void orElse(Consumer<T> consumer) {
    // Do nothing as a match has already been found.
  }
}
