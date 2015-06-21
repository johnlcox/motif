package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor3;
import com.leacox.motif.function.Consumer3;
import com.leacox.motif.function.Function3;
import com.leacox.motif.tuple.Tuple3;

/**
 * @author John Leacox
 */
abstract class Matching3<T, U extends T, A, B, C> {
  private final Extractor3<U, A, B, C> extractor;

  Matching3(Extractor3<U, A, B, C> extractor) {
    this.extractor = extractor;
  }

  <R> FluentMatchingR<T, R> get(
      FluentMatchingR<T, R> fluentMatchingR, Function3<A, B, C, R> function) {
    fluentMatchingR.addPattern(
        Pattern.of(
            t -> extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                .unapply((U) t).isPresent(),
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
            t -> extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                .unapply((U) t).isPresent(),
            t -> {
              Tuple3<A, B, C> tuple3 = extractor.unapply((U) t).get();
              consumer.accept(tuple3.first(), tuple3.second(), tuple3.third());
            }
        )
    );

    return fluentMatchingC;
  }
}
