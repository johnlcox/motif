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

import com.leacox.motif.MatchException;
import com.leacox.motif.extraction.DecomposableMatchBuilder0;
import com.leacox.motif.extraction.DecomposableMatchBuilder1;
import com.leacox.motif.extraction.DecomposableMatchBuilder2;
import com.leacox.motif.extraction.DecomposableMatchBuilder3;
import com.leacox.motif.function.Consumer0;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author John Leacox
 */
public final class FluentMatchingC<T> {
  final T value;
  private final List<ConsumablePattern<T>> patterns = new ArrayList<>();

  FluentMatchingC(T value) {
    this.value = value;
  }

  public void addPattern(ConsumablePattern<T> pattern) {
    patterns.add(pattern);
  }

  public <U extends T> OngoingMatchingC0<T, U> when(
      DecomposableMatchBuilder0<U> decomposableMatchBuilder) {
    return new OngoingMatchingC0<>(this, decomposableMatchBuilder.build());
  }

  public <U extends T, A> OngoingMatchingC1<T, U, A> when(
      DecomposableMatchBuilder1<U, A> decomposableMatchBuilder) {
    return new OngoingMatchingC1<>(this, decomposableMatchBuilder.build());
  }

  public <U extends T, A, B> OngoingMatchingC2<T, U, A, B> when(
      DecomposableMatchBuilder2<U, A, B> decomposableMatchBuilder) {
    return new OngoingMatchingC2<>(this, decomposableMatchBuilder.build());
  }

  public <U extends T, A, B, C> OngoingMatchingC3<T, U, A, B, C> when(
      DecomposableMatchBuilder3<U, A, B, C> decomposableMatchBuilder) {
    return new OngoingMatchingC3<>(this, decomposableMatchBuilder.build());
  }

  public void doMatch() {
    boolean matchFound = false;
    for (ConsumablePattern<T> pattern : patterns) {
      if (pattern.matches(value)) {
        pattern.consume(value);
        matchFound = true;
        break;
      }
    }

    if (!matchFound) {
      throw new MatchException("No match found for " + value);
    }
  }

  // TODO: Should these execute the match immediately but skip the MatchException?
  public FluentMatchingC<T> orElse(Consumer<T> consumer) {
    patterns.add(ConsumablePattern.of(t -> true, consumer::accept));
    return this;
  }

  public FluentMatchingC<T> orElse(Consumer0 consumer) {
    patterns.add(ConsumablePattern.of(t -> true, t -> consumer.accept()));
    return this;
  }

  public FluentMatchingC<T> orElse() {
    patterns.add(ConsumablePattern.of(t -> true, t -> doNothing()));
    return this;
  }

  private void doNothing() {
  }
}
