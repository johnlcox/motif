package com.leacox.motif.fluent.extractor;

import com.leacox.motif.tuple.Tuple3;

import java.util.Optional;

/**
 * @author John Leacox
 */
public interface Extractor3<T, A, B, C> {
  //T apply(A a, B b, C c);

  Optional<Tuple3<A, B, C>> unapply(T t);

  Class getExtractorClass();
}
