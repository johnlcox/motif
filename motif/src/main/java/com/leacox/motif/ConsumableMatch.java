package com.leacox.motif;

import com.leacox.motif.pattern.ConsumablePattern;

import java.util.function.Consumer;

/**
 * @author John Leacox
 */
public interface ConsumableMatch<T> {
  ConsumableMatch<T> when(ConsumablePattern<T> pattern);

  void orElse(Consumer<T> consumer);
}
