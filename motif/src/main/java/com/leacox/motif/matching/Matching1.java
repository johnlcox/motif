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

import com.leacox.motif.extract.Extractor1;

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
