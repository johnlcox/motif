package com.leacox.motif.fluent.extractor;

import com.leacox.motif.tuple.Tuple2;

import java.util.Optional;

/**
 * @author John Leacox
 */
public interface Extractor2<T, A, B> {
  //T apply(A a, B b);

  Optional<Tuple2<A, B>> unapply(T t);
}
