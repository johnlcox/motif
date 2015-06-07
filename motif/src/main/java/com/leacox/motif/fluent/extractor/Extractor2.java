package com.leacox.motif.fluent.extractor;

import com.leacox.motif.tuple.Tuple2;

import java.util.Optional;

/**
 * @author John Leacox
 */
public interface Extractor2<T, A, B> {
  public T apply(A a, B b);

  public Optional<Tuple2<A, B>> unapply(T t);
}
