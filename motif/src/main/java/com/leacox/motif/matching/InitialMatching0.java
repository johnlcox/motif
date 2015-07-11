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
 * Fluent API for specifying an action to take on a match with 0 parameters.
 *
 * @author John Leacox
 */
public final class InitialMatching0<T, U extends T> extends Matching0<T, U> {
  private final T value;

  InitialMatching0(Extractor0<U> extractor, T value) {
    super(extractor);

    this.value = value;
  }

  /**
   * Sets a {@link Supplier} to execute if this matches.
   */
  public <R> FluentMatchingR<T, R> get(Supplier<R> supplier) {
    return get(new FluentMatchingR<>(value), supplier);
  }

  /**
   * Sets a {@link Consumer0} to execute if this matches.
   */
  public FluentMatchingC<T> then(Consumer0 consumer) {
    return then(new FluentMatchingC<>(value), consumer);
  }
}
