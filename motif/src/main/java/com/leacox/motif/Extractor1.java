package com.leacox.motif;

import java.util.Optional;

/**
 * @author John Leacox
 */
public interface Extractor1<T, A> {
  public T apply(A a);

  public Optional<A> unapply(T t);
}
