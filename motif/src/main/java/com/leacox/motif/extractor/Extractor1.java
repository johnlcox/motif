package com.leacox.motif.extractor;

import java.util.Optional;

/**
 * @author John Leacox
 */
public interface Extractor1<T, A> {
  //T apply(A a);

  Optional<A> unapply(T t);

  Class getExtractorClass();
}
