package com.leacox.motif.pattern;

/**
 * @author John Leacox
 */
public interface ConsumablePattern<T> {
  public boolean matches(T value);

  public void consume(T value);
}
