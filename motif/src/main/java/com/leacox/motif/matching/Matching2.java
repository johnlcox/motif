package com.leacox.motif.matching;

import com.leacox.motif.extraction.Extractor2;
import com.leacox.motif.function.Consumer2;
import com.leacox.motif.function.Function2;
import com.leacox.motif.tuple.Tuple2;

/**
 * @author John Leacox
 */
abstract class Matching2<T, U extends T, A, B> {
  private final Extractor2<U, A, B> extractor;

  Matching2(Extractor2<U, A, B> extractor) {
    this.extractor = extractor;
  }

  <R> FluentMatchingR<T, R> get(
      FluentMatchingR<T, R> fluentMatchingR, Function2<A, B, R> function) {
    fluentMatchingR.addPattern(
        Pattern.of(
            t -> extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                .unapply((U) t).isPresent(),
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
            t -> extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                .unapply((U) t).isPresent(),
            t -> {
              Tuple2<A, B> tuple2 = extractor.unapply((U) t).get();
              consumer.accept(tuple2.first(), tuple2.second());
            }
        )
    );

    return fluentMatchingC;
  }
}
