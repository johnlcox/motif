package com.leacox.motif;

import com.leacox.motif.function.Consumer0;
import com.leacox.motif.pattern.ConsumablePattern;

/**
 * @author John Leacox
 */
class NoConsumableMatch<T> implements ConsumableMatch<T> {
  private final T value;

  public NoConsumableMatch(T value) {
    this.value = value;
  }

  @Override
  public ConsumableMatch<T> when(ConsumablePattern<T> pattern) {
    if (pattern.matches(value)) {
      pattern.consume(value);
      return new YesConsumableMatch<>();
    }

    return this;
  }

  @Override
  public void orElse(Consumer0 consumer) {
    consumer.accept();
  }
}
