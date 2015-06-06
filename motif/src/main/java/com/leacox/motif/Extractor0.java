package com.leacox.motif;

/**
 * @author John Leacox
 */
public interface Extractor0<T> {
  public T apply();

  public boolean unapply(T t);
}
