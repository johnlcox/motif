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

package com.leacox.motif;

/**
 * A wrapper for exact matching.
 *
 * @author John Leacox
 */
public class MatchesExact<T> {
  public final T t;

  private MatchesExact(T t) {
    this.t = t;
  }

  /**
   * Creates a new instance of {@link MatchesExact} for the specified value.
   */
  public static <T> MatchesExact<T> eq(T t) {
    return new MatchesExact<>(t);
  }
}
