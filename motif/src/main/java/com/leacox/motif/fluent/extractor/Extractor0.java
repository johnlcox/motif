package com.leacox.motif.fluent.extractor;

/**
 * @author John Leacox
 */
public interface Extractor0<T> {
  public T apply();

  public boolean unapply(T t);
}
