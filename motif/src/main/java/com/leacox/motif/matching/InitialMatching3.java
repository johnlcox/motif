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

/**
 * Fluent API for specifying an action to take on a match with 3 parameters.
 *
 * @author John Leacox
 */
public final class InitialMatching3<T, U extends T, A, B, C> extends Matching3<T, U, A, B, C> {
  private final T value;

  InitialMatching3(Extractor3<U, A, B, C> extractor, T value) {
    super(extractor);

    this.value = value;
  }

  /**
   * Sets a {@link Function3} to execute if this matches.
   */
  public <R> FluentMatchingR<T, R> get(Function3<A, B, C, R> function) {
    return get(new FluentMatchingR<>(value), function);
  }

  /**
   * Sets a {@link Consumer3} to execute if this matches.
   */
  public FluentMatchingC<T> then(Consumer3<A, B, C> consumer) {
    return then(new FluentMatchingC<>(value), consumer);
  }
}
