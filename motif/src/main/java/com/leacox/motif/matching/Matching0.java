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

import com.leacox.motif.extract.Extractor0;
import com.leacox.motif.function.Consumer0;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
abstract class Matching0<T, U extends T> {
  private final Extractor0<U> extractor;

  Matching0(
      Extractor0<U> extractor) {
    this.extractor = extractor;
  }

  <R> FluentMatchingR<T, R> get(
      FluentMatchingR<T, R> fluentMatchingR, Supplier<R> supplier) {
    fluentMatchingR.addPattern(
        Pattern.of(
            t -> extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                .unapply((U) t), t -> supplier.get()));

    return fluentMatchingR;
  }

  FluentMatchingC<T> then(FluentMatchingC<T> fluentMatchingC, Consumer0 consumer) {
    fluentMatchingC.addPattern(
        ConsumablePattern.of(
            t -> extractor.getExtractorClass().isAssignableFrom(t.getClass()) && extractor
                .unapply((U) t), t -> consumer.accept()));

    return fluentMatchingC;
  }
}
