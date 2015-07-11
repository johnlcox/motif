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

import com.leacox.motif.MatchesAny;
import com.leacox.motif.MatchesExact;
import com.leacox.motif.Motif;
import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.DecomposableMatchBuilder3;
import com.leacox.motif.extract.matchers.ArgumentMatchers;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;

import java.util.List;

/**
 * The start of the fluent matching API.
 *
 * <p>Rather than instantiating this class directly, the {@link Motif#match(Object)} should be used.
 *
 * @author John Leacox
 */
public final class FluentMatching<T> {
  private final T value;

  /**
   * Creates a new instance of {@link FluentMatching}.
   *
   * <p>Rather than instantiating this class directly, the {@link Motif#match(Object)} should be
   * used.
   */
  public FluentMatching(T value) {
    this.value = value;
  }

  /**
   * Specifies an exact match and then returns a fluent interface for specifying the action to take
   * if the value matches this case.
   */
  public <U extends T> InitialMatching0<T, U> when(MatchesExact<U> o) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(o.t));
    return new InitialMatching0<>(
        new DecomposableMatchBuilder0<>(matchers, new IdentityFieldExtractor<U>()).build(), value);
  }

  /**
   * Specifies a wildcard match and then returns a fluent interface for specifying the action to
   * take if the value matches this case.
   */
  public <U extends T> InitialMatching1<T, U, U> when(MatchesAny<U> o) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
    return new InitialMatching1<>(
        new DecomposableMatchBuilder1<U, U>(matchers, 0, new IdentityFieldExtractor<>()).build(),
        value);
  }

  /**
   * Specifies an match on a decomposing matcher with 0 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T> InitialMatching0<T, U> when(
      DecomposableMatchBuilder0<U> decomposableMatchBuilder) {
    return new InitialMatching0<>(decomposableMatchBuilder.build(), value);
  }

  /**
   * Specifies an match on a decomposing matcher with 1 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T, A> InitialMatching1<T, U, A> when(
      DecomposableMatchBuilder1<U, A> decomposableMatchBuilder) {
    return new InitialMatching1<>(decomposableMatchBuilder.build(), value);
  }

  /**
   * Specifies an match on a decomposing matcher with 2 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T, A, B> InitialMatching2<T, U, A, B> when(
      DecomposableMatchBuilder2<U, A, B> decomposableMatchBuilder) {
    return new InitialMatching2<>(decomposableMatchBuilder.build(), value);
  }

  /**
   * Specifies an match on a decomposing matcher with 3 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T, A, B, C> InitialMatching3<T, U, A, B, C> when(
      DecomposableMatchBuilder3<U, A, B, C> decomposableMatchBuilder) {
    return new InitialMatching3<>(decomposableMatchBuilder.build(), value);
  }
}
