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
import com.leacox.motif.MatchesAny;
import com.leacox.motif.MatchesExact;
import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.DecomposableMatchBuilder3;
import com.leacox.motif.extract.matchers.ArgumentMatchers;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Fluent matching for actions with a resulting value.
 *
 * @author John Leacox
 */
public final class FluentMatchingR<T, R> {
  private final T value;
  private final List<Pattern<T, R>> patterns = new ArrayList<>();

  FluentMatchingR(T value) {
    this.value = value;
  }

  void addPattern(Pattern<T, R> pattern) {
    patterns.add(pattern);
  }

  /**
   * Specifies an exact match and then returns a fluent interface for specifying the action to take
   * if the value matches this case.
   */
  public <U extends T> OngoingMatchingR0<T, U, R> when(MatchesExact<U> o) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(o.t));
    return new OngoingMatchingR0<>(
        this, new DecomposableMatchBuilder0<>(matchers, new IdentityFieldExtractor<U>()).build());
  }

  /**
   * Specifies a wildcard match and then returns a fluent interface for specifying the action to
   * take if the value matches this case.
   */
  public <U extends T> OngoingMatchingR1<T, U, U, R> when(MatchesAny<U> o) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
    return new OngoingMatchingR1<>(
        this,
        new DecomposableMatchBuilder1<U, U>(matchers, 0, new IdentityFieldExtractor<>()).build());
  }

  /**
   * Specifies an match on a decomposing matcher with 0 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T> OngoingMatchingR0<T, U, R> when(
      DecomposableMatchBuilder0<U> decomposableMatchBuilder) {
    return new OngoingMatchingR0<>(this, decomposableMatchBuilder.build());
  }

  /**
   * Specifies an match on a decomposing matcher with 1 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T, A> OngoingMatchingR1<T, U, A, R> when(
      DecomposableMatchBuilder1<U, A> decomposableMatchBuilder) {
    return new OngoingMatchingR1<>(this, decomposableMatchBuilder.build());
  }

  /**
   * Specifies an match on a decomposing matcher with 2 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T, A, B> OngoingMatchingR2<T, U, A, B, R> when(
      DecomposableMatchBuilder2<U, A, B> decomposableMatchBuilder) {
    return new OngoingMatchingR2<>(this, decomposableMatchBuilder.build());
  }

  /**
   * Specifies an match on a decomposing matcher with 3 extracted fields and then returns a fluent
   * interface for specifying the action to take if the value matches this case.
   */
  public <U extends T, A, B, C> OngoingMatchingR3<T, U, A, B, C, R> when(
      DecomposableMatchBuilder3<U, A, B, C> decomposableMatchBuilder) {
    return new OngoingMatchingR3<>(this, decomposableMatchBuilder.build());
  }

  /**
   * Runs through the possible matches and executes the specified action of the first match and
   * returns the result.
   *
   * @throws MatchException if no match is found.
   */
  public R getMatch() {
    for (Pattern<T, R> pattern : patterns) {
      if (pattern.matches(value)) {
        return pattern.apply(value);
      }
    }

    throw new MatchException("No match found for " + value);
  }

  /**
   * Sets a {@link Function} to be run if no match is found.
   */
  public FluentMatchingR<T, R> orElse(Function<T, R> function) {
    patterns.add(Pattern.of(t -> true, function::apply));
    return this;
  }

  /**
   * Sets a {@link Supplier} to be run if no match is found.
   */
  public FluentMatchingR<T, R> orElse(Supplier<R> supplier) {
    patterns.add(Pattern.of(t -> true, t -> supplier.get()));
    return this;
  }

  /**
   * Sets a {@code result} to be returned from {@link FluentMatchingR#getMatch()} if no match is
   * found.
   */
  public FluentMatchingR<T, R> orElse(R result) {
    patterns.add(Pattern.of(t -> true, t -> result));
    return this;
  }
}
