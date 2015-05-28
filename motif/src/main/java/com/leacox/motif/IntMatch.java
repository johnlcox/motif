package com.leacox.motif;

import com.leacox.motif.pattern.IntPattern;

/**
 * @author John Leacox
 */
public interface IntMatch<R> {
  IntMatch<R> when(IntPattern<R> pattern);

  R get();
}
