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

import com.leacox.motif.extract.Extractor3;
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
