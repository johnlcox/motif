package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor3;
import com.leacox.motif.function.Consumer3;
import com.leacox.motif.function.Function3;
import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;
import com.leacox.motif.tuple.Tuple3;

import com.leacox.motif.matchers.Matcher;

import java.util.Optional;

/**
 * @author John Leacox
 */
abstract class Matching3<T, U extends T, A, B, C> {
  private final Extractor3<U, A, B, C> extractor;
  private final Matcher<A> toMatchA;
  private final Matcher<B> toMatchB;
  private final Matcher<C> toMatchC;

  Matching3(
      Extractor3<U, A, B, C> extractor, Matcher<A> toMatchA, Matcher<B> toMatchB,
      Matcher<C> toMatchC) {
    this.extractor = extractor;
    this.toMatchA = toMatchA;
    this.toMatchB = toMatchB;
    this.toMatchC = toMatchC;
  }

  <R> FluentMatchingR<T, R> get(
      FluentMatchingR<T, R> fluentMatchingR, Function3<A, B, C, R> function) {
    fluentMatchingR.addPattern(
        Pattern.of(
            t -> {
              if (!extractor.getExtractorClass().isAssignableFrom(t.getClass())) {
                return false;
              }

              Optional<Tuple3<A, B, C>> opt = extractor.unapply((U) t);
              if (opt.isPresent()) {
                Tuple3<A, B, C> tuple3 = opt.get();
                return toMatchA.matches(tuple3.first()) && toMatchB.matches(tuple3.second())
                    && toMatchC.matches(tuple3.third());
              }

              return false;
            },
            t -> {
              Tuple3<A, B, C> tuple3 = extractor.unapply((U) t).get();
              return function.apply(tuple3.first(), tuple3.second(), tuple3.third());
            }
        )
    );

    return fluentMatchingR;
  }

  FluentMatchingC<T> then(FluentMatchingC<T> fluentMatchingC, Consumer3<A, B, C> consumer) {
    fluentMatchingC.addPattern(
        ConsumablePattern.of(
            t -> {
              if (!extractor.getExtractorClass().isAssignableFrom(t.getClass())) {
                return false;
              }

              Optional<Tuple3<A, B, C>> opt = extractor.unapply((U) t);
              if (opt.isPresent()) {
                Tuple3<A, B, C> tuple3 = opt.get();
                return toMatchA.matches(tuple3.first()) && toMatchB.matches(tuple3.second())
                    && toMatchC.matches(tuple3.third());
              }

              return false;
            },
            t -> {
              Tuple3<A, B, C> tuple3 = extractor.unapply((U) t).get();
              consumer.accept(tuple3.first(), tuple3.second(), tuple3.third());
            }
        )
    );

    return fluentMatchingC;
  }
}
