package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor1;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
abstract class Matching1<T, U extends T, A> {
  private final Extractor1<U, A> extractor;

  Matching1(
      Extractor1<U, A> extractor) {
    this.extractor = extractor;
  }

  <R> FluentMatchingR<T, R> get(
      FluentMatchingR<T, R> fluentMatchingR, Function<A, R> function) {
    fluentMatchingR.addPattern(
        Pattern.of(
            t -> {
              return extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                  .unapply((U) t).isPresent();
            },
            t -> {
              A a = extractor.unapply((U) t).get();
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
              return extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                  .unapply((U) t).isPresent();
            },
            t -> {
              A a = extractor.unapply((U) t).get();
              consumer.accept(a);
            }
        )
    );

    return fluentMatchingC;
  }
}
