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

import java.util.function.Function;

/**
 * @author John Leacox
 */
public final class OngoingMatchingR1<T, U extends T, A, R> extends Matching1<T, U, A> {
  private final FluentMatchingR<T, R> fluentMatchingR;

  OngoingMatchingR1(FluentMatchingR<T, R> fluentMatchingR, Extractor1<U, A> extractor) {
    super(extractor);

    this.fluentMatchingR = fluentMatchingR;
  }

  public FluentMatchingR<T, R> get(Function<A, R> function) {
    return get(fluentMatchingR, function);
  }
}
