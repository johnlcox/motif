package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor1;
import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;

import org.hamcrest.Matcher;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
abstract class Matching1<T, A> {
  private final Extractor1<T, A> extractor;
  private final Matcher<A> toMatchA;

  Matching1(
      Extractor1<T, A> extractor, Matcher<A> toMatchA) {
    this.extractor = extractor;
    this.toMatchA = toMatchA;
  }

  <R> FluentMatchingR<T, R> get(
      FluentMatchingR<T, R> fluentMatchingR, Function<A, R> function) {
    fluentMatchingR.addPattern(
        Pattern.of(
            t -> {
              Optional<A> opt = extractor.unapply(t);
              if (opt.isPresent()) {
                A a = opt.get();
                return toMatchA.matches(a);
              }

              return false;
            },
            t -> {
              A a = extractor.unapply(t).get();
              return function.apply(a);
            }
        )
    );

    return fluentMatchingR;
  }

  FluentMatchingC<T> then(FluentMatchingC<T> fluentMatchingC, Consumer<A> consumer) {
    fluentMatchingC.addPattern(
        ConsumablePattern.of(
            t -> {
              Optional<A> opt = extractor.unapply(t);
              if (opt.isPresent()) {
                A a = opt.get();
                return toMatchA.matches(a);
              }

              return false;
            },
            t -> {
              A a = extractor.unapply(t).get();
              consumer.accept(a);
            }
        )
    );

    return fluentMatchingC;
  }
}
