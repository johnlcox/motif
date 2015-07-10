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

/**
 * @author John Leacox
 */
public final class OngoingMatchingC0<T, U extends T> extends Matching0<T, U> {
  private final FluentMatchingC<T> fluentMatchingC;

  OngoingMatchingC0(FluentMatchingC<T> fluentMatchingC, Extractor0<U> extractor) {
    super(extractor);

    this.fluentMatchingC = fluentMatchingC;
  }

  public FluentMatchingC<T> then(Consumer0 consumer) {
    return then(fluentMatchingC, consumer);
  }
}
