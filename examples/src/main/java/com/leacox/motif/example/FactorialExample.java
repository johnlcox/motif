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

package com.leacox.motif.example;

import static com.leacox.motif.MatchesAny.any;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.PrimitiveCases.caseLong;

/**
 * An example of using pattern matching for implementing factorial.
 *
 * @author John Leacox
 */
public class FactorialExample {
  /**
   * A traditional implementation of factorial with a conditional.
   */
  public long factConditional(long n) {
    if (n == 0) {
      return 1;
    } else {
      return n * factConditional(n - 1);
    }
  }

  /**
   * An implementation of factorial using motif pattern matching.
   */
  public long factMatching(long n) {
    return match(n)
        .when(caseLong(0)).get(() -> 1L)
        .when(caseLong(any())).get(i -> i * factMatching(i - 1))
        .getMatch();
  }
}
