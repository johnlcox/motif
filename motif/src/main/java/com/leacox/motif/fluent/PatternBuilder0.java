package com.leacox.motif.fluent;

import com.leacox.motif.Extractor0;
import com.leacox.motif.function.Consumer0;
import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public class PatternBuilder0<T, A> {
  private final Extractor0<T> extractor;
  private final T value;

  PatternBuilder0(Extractor0<T> extractor, T value) {
    this.extractor = extractor;
    this.value = value;
  }

  public <R> FluentMatchingR<T, A, R> is(Supplier<R> supplier) {
    FluentMatchingR<T, A, R> fluentMatchingR = new FluentMatchingR<>(value);

    fluentMatchingR.addPattern(Pattern.of(extractor::unapply, t -> supplier.get()));

    return fluentMatchingR;
  }

  public FluentMatchingC<T, A> is(Consumer0 consumer) {
    FluentMatchingC<T, A> fluentMatchingC = new FluentMatchingC<>(value);

    fluentMatchingC.addPattern(ConsumablePattern.of(extractor::unapply, t -> consumer.accept()));

    return fluentMatchingC;
  }
}
