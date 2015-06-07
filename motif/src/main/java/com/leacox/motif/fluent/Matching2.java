package com.leacox.motif.fluent;

import com.leacox.motif.fluent.extractor.Extractor2;
import com.leacox.motif.function.Consumer2;
import com.leacox.motif.function.Function2;
import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;
import com.leacox.motif.tuple.Tuple2;

import org.hamcrest.Matcher;

import java.util.Optional;

/**
 * @author John Leacox
 */
abstract class Matching2<T, U, A, B> {
  private final Extractor2<U, A, B> extractor;
  private final Matcher<A> toMatchA;
  private final Matcher<B> toMatchB;

  Matching2(
      Extractor2<U, A, B> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB) {
    this.extractor = extractor;
    this.toMatchA = toMatchA;
    this.toMatchB = toMatchB;
  }

  <R> FluentMatchingR<T, R> get(
      FluentMatchingR<T, R> fluentMatchingR, Function2<A, B, R> function) {
    fluentMatchingR.addPattern(
        Pattern.of(
            t -> {
              if (!extractor.getExtractorClass().isAssignableFrom(t.getClass())) {
                return false;
              }

              Optional<Tuple2<A, B>> opt = extractor.unapply((U) t);
              if (opt.isPresent()) {
                Tuple2<A, B> tuple2 = opt.get();
                return toMatchA.matches(tuple2.first()) && toMatchB.matches(tuple2.second());
              }

              return false;
            },
            t -> {
              Tuple2<A, B> tuple2 = extractor.unapply((U) t).get();
              return function.apply(tuple2.first(), tuple2.second());
            }
        )
    );

    return fluentMatchingR;
  }

  FluentMatchingC<T> then(FluentMatchingC<T> fluentMatchingC, Consumer2<A, B> consumer) {
    fluentMatchingC.addPattern(
        ConsumablePattern.of(
            t -> {
              if (!extractor.getExtractorClass().isAssignableFrom(t.getClass())) {
                return false;
              }

              Optional<Tuple2<A, B>> opt = extractor.unapply((U) t);
              if (opt.isPresent()) {
                Tuple2<A, B> tuple2 = opt.get();
                return toMatchA.matches(tuple2.first()) && toMatchB.matches(tuple2.second());
              }

              return false;
            },
            t -> {
              Tuple2<A, B> tuple2 = extractor.unapply((U) t).get();
              consumer.accept(tuple2.first(), tuple2.second());
            }
        )
    );

    return fluentMatchingC;
  }
}
