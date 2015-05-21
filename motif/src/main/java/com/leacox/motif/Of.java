package com.leacox.motif;

import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;

/**
 * @author John Leacox
 */
public final class Of<T> {
  private final T value;

  Of(T value) {
    this.value = value;
  }

  public <R> Match<T, R> when(Pattern<T, R> pattern) {
    return pattern.matches(value) ? new YesMatch<>(pattern.apply(value)) : new NoMatch<>(value);
  }

  public ConsumableMatch<T> when(ConsumablePattern<T> pattern) {
    if (pattern.matches(value)) {
      pattern.consume(value);
      return new YesConsumableMatch<>();
    }

    return new NoConsumableMatch<>(value);
  }
}
