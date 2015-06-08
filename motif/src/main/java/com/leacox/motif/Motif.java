package com.leacox.motif;

import com.leacox.motif.matching.FluentMatching;

/**
 * @author John Leacox
 */
public final class Motif {
  private Motif() {
  }

  public static <T> FluentMatching<T> match(T value) {
    return new FluentMatching<>(value);
  }
}
