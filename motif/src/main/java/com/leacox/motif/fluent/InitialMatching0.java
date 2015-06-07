package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor0;
import com.leacox.motif.function.Consumer0;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class InitialMatching0<T, U> extends Matching0<T, U> {
  private final T value;

  InitialMatching0(Extractor0<U> extractor, T value) {
    super(extractor);

    this.value = value;
  }

  public <R> FluentMatchingR<T, R> get(Supplier<R> supplier) {
    return get(new FluentMatchingR<>(value), supplier);
  }

  public FluentMatchingC<T> then(Consumer0 consumer) {
    return then(new FluentMatchingC<>(value), consumer);
  }
}
