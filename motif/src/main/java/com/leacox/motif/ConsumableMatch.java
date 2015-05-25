package com.leacox.motif;

import com.leacox.motif.function.Consumer0;
import com.leacox.motif.pattern.ConsumablePattern;

/**
 * @author John Leacox
 */
public interface ConsumableMatch<T> {
  ConsumableMatch<T> when(ConsumablePattern<T> pattern);

  void orElse(Consumer0 consumer);
}
