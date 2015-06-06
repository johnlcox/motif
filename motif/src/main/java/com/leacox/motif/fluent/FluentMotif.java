package com.leacox.motif.fluent;

/**
 * @author John Leacox
 */
public class FluentMotif {

  public static <T> FluentMatching<T> match(T value) {
    return new FluentMatching<>(value);
  }
}
