package com.leacox.motif.pattern;

/**
 * @author John Leacox
 */
public interface Pattern<T, R> {
  public boolean matches(T value);

  public R apply(T value);
}
