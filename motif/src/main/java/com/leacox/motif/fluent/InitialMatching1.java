package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor1;

import org.hamcrest.Matcher;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
public final class InitialMatching1<T, A> extends Matching1<T, A> {
  private final T value;

  InitialMatching1(
      Extractor1<T, A> extractor, T value, Matcher<A> toMatchA) {
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
