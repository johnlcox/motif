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

import com.leacox.motif.matching.FluentMatching;

/**
 * Motif provides a fluent API for Scala-like pattern matching.
 *
 * <p>For more information and examples see documentation on {@link Motif#match(Object)}.
 *
 * @author John Leacox
 */
public final class Motif {
  private Motif() {
  }

  /**
   * Begins pattern matching on the specified value.
   *
   * <p>This is the entry point into the fluent pattern matching API. References <strong>should
   * not</strong> be created to the returned objects from the fluent API. The only reference that
   * should be created is from the result of {@code getMatch()}.
   *
   * <p>Motif provides many built in matching cases, including:
   * <ul>
   *   <li>{@link com.leacox.motif.cases.PrimitiveCases Primitives}</li>
   *   <li>{@link com.leacox.motif.cases.OptionalCases Optional}</li>
   *   <li>{@link com.leacox.motif.cases.ListConsCases Lists}</li>
   *   <li>{@link com.leacox.motif.cases.TypeOfCases TypeOf (instanceof)}</li>
   *   <li>{@link com.leacox.motif.cases.Tuple1Cases Tuple1}</li>
   *   <li>{@link com.leacox.motif.cases.Tuple2Cases Tuple2}</li>
   *   <li>{@link com.leacox.motif.cases.Tuple3Cases Tuple3}</li>
   *   <li>{@link com.leacox.motif.cases.Case1Cases Case1 classes}</li>
   *   <li>{@link com.leacox.motif.cases.Case2Cases Case2 classes}</li>
   *   <li>{@link com.leacox.motif.cases.Case3Cases Case3 classes}</li>
   * </ul>
   *
   * <p>In addition the matching case API is extremely extensible. All that is needed to create a
   * new matching case is to implement one of the {@code ExtractorN} (e.g.
   * {@link com.leacox.motif.extract.Extractor1 Extractor1}) and
   * {@link com.leacox.motif.extract.FieldExtractor} for your type, and then create a new
   * instance of {@code DecomposableMatchBuilderN} with the field extractor for your type and the
   * args to match on. The easiest way to do this is to create a static method that can be imported
   * like {@link com.leacox.motif.cases.OptionalCases#some(MatchesAny)
   * OptionalCases#some(MatchesAny)}.
   *
   * <p>The following are some basic pattern matching examples:
   * <pre>
   * {@code
   *
   * // Matching on Optional
   * Optional<Person> personOpt = getPerson();
   * match(personOpt)
   *     .when(some(any())).then(person -> doStuff(person))
   *     .when(none()).then(() -> System.out.println("Person not found"))
   *     .doMatch();
   * }
   * </pre>
   *
   * <pre>
   * {@code
   *
   * // Fizzbuzz (Matching on Tuples)
   * IntStream.range(0, 101).forEach(
   *     n -> System.out.println(
   *         match(Tuple2.of(n % 3, n % 5))
   *             .when(tuple2(0, 0)).get(() -> "FizzBuzz")
   *             .when(tuple2(0, any())).get(y -> "Fizz")
   *             .when(tuple2(any(), 0)).get(x -> "Buzz")
   *             .orElse(String.valueOf(n))
   *             .getMatch()
   *     )
   * );
   * }
   * </pre>
   *
   * @param value the value the match on
   * @param <T> the type of the value
   * @return FluentMatching object used to fluently build pattern matches. <strong>Do not</strong>
   *     create a reference to the returned object.
   */
  public static <T> FluentMatching<T> match(T value) {
    return new FluentMatching<>(value);
  }
}
