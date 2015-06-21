package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor3;
import com.leacox.motif.function.Consumer3;
import com.leacox.motif.function.Function3;

/**
 * @author John Leacox
 */
public final class InitialMatching3<T, U extends T, A, B, C> extends Matching3<T, U, A, B, C> {
  private final T value;

  InitialMatching3(Extractor3<U, A, B, C> extractor, T value) {
    super(extractor);

    this.value = value;
  }

  public <R> FluentMatchingR<T, R> get(Function3<A, B, C, R> function) {
    return get(new FluentMatchingR<>(value), function);
  }

  public FluentMatchingC<T> then(Consumer3<A, B, C> consumer) {
    return then(new FluentMatchingC<>(value), consumer);
  }
}
