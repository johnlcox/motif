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

import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.DecomposableMatchBuilder3;

/**
 * @author John Leacox
 */
public final class FluentMatching<T> {
  final T value;

  public FluentMatching(T value) {
    this.value = value;
  }

  public <U extends T> InitialMatching0<T, U> when(
      DecomposableMatchBuilder0<U> decomposableMatchBuilder) {
    return new InitialMatching0<>(decomposableMatchBuilder.build(), value);
  }

  public <U extends T, A> InitialMatching1<T, U, A> when(
      DecomposableMatchBuilder1<U, A> decomposableMatchBuilder) {
    return new InitialMatching1<>(decomposableMatchBuilder.build(), value);
  }

  public <U extends T, A, B> InitialMatching2<T, U, A, B> when(
      DecomposableMatchBuilder2<U, A, B> decomposableMatchBuilder) {
    return new InitialMatching2<>(decomposableMatchBuilder.build(), value);
  }

  public <U extends T, A, B, C> InitialMatching3<T, U, A, B, C> when(
      DecomposableMatchBuilder3<U, A, B, C> decomposableMatchBuilder) {
    return new InitialMatching3<>(decomposableMatchBuilder.build(), value);
  }
}
