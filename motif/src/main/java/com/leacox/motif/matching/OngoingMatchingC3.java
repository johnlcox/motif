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

import com.leacox.motif.extraction.Extractor3;
import com.leacox.motif.function.Consumer3;

/**
 * @author John Leacox
 */
public class OngoingMatchingC3<T, U extends T, A, B, C> extends Matching3<T, U, A, B, C> {
  private final FluentMatchingC<T> fluentMatchingC;

  OngoingMatchingC3(FluentMatchingC<T> fluentMatchingC, Extractor3<U, A, B, C> extractor) {
    super(extractor);

    this.fluentMatchingC = fluentMatchingC;
  }

  public FluentMatchingC<T> then(Consumer3<A, B, C> consumer) {
    return then(fluentMatchingC, consumer);
  }
}
