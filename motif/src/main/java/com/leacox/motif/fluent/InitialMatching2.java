package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor2;
import com.leacox.motif.function.Consumer2;
import com.leacox.motif.function.Function2;

import org.hamcrest.Matcher;

/**
 * @author John Leacox
 */
public final class InitialMatching2<T, U, A, B> extends Matching2<T, U, A, B> {
  private final T value;

  InitialMatching2(
      Extractor2<U, A, B> extractor, T value, Matcher<A> toMatchA, Matcher<B> toMatchB) {
    super(extractor, toMatchA, toMatchB);

    this.value = value;
  }

  public <R> FluentMatchingR<T, R> get(Function2<A, B, R> function) {
    return get(new FluentMatchingR<>(value), function);
  }

  public FluentMatchingC<T> then(Consumer2<A, B> consumer) {
    return then(new FluentMatchingC<>(value), consumer);
  }
}
