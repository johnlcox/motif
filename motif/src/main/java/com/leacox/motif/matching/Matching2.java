/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
