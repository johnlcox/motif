package com.leacox.motif.extraction;

/**
 * @author John Leacox
 */
public interface Extractor0<T> {
  //T apply();

  boolean unapply(T t);

  Class getExtractorClass();
}
