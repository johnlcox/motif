package com.leacox.motif;

import com.leacox.motif.function.Consumer0;
import com.leacox.motif.pattern.ConsumablePattern;

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
  public void orElse(Consumer0 consumer) {
    // Do nothing as a match has already been found.
  }
}
