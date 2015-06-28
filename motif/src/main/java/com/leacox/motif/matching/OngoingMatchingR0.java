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

import com.leacox.motif.extraction.Extractor0;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR0<T, U extends T, R> extends Matching0<T, U> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR0(FluentMatchingR<T, R> fluentMatchingR, Extractor0<U> extractor) {
    super(extractor);
    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Supplier<R> supplier) {
    return get(fluentMatchingR, supplier);
  }
}
