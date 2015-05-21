package com.leacox.motif;

import com.leacox.motif.pattern.Pattern;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public interface Match<T, R> {
  Match<T, R> when(Pattern<T, R> pattern);

  MatchEnd<R> orElse(R result);

  MatchEnd<R> orElse(Supplier<R> supplier);

  R get();
}
