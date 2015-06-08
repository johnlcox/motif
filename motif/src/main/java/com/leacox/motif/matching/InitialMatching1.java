package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor1;

import org.hamcrest.Matcher;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
public final class InitialMatching1<T, U extends T, A> extends Matching1<T, U, A> {
  private final T value;

  InitialMatching1(
      Extractor1<U, A> extractor, T value, Matcher<A> toMatchA) {
    super(extractor, toMatchA);

    this.value = value;
  }

  public <R> FluentMatchingR<T, R> get(Function<A, R> function) {
    return get(new FluentMatchingR<>(value), function);
  }

  public FluentMatchingC<T> then(Consumer<A> consumer) {
    return then(new FluentMatchingC<>(value), consumer);
  }
}
