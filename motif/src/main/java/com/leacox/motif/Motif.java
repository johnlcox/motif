package com.leacox.motif;

import com.leacox.motif.decomposition.MatchesAny;
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
   * should be created it is from the result of {@code getMatch()}.
   *
   * <p>Motif provides many built in matching cases, including:
   * <ul>
   *   <li>{@link com.leacox.motif.cases.PrimitiveCases Primitives}</li>
   *   <li>{@link com.leacox.motif.cases.OptionalCases Optional}</li>
   *   <li>{@link com.leacox.motif.cases.ListCases Lists}</li>
   *   <li>{@link com.leacox.motif.cases.TypeOfCases TypeOf (instanceof)}</li>
   *   <li>{@link com.leacox.motif.cases.TupleCases Tuples}</li>
   *   <li>{@link com.leacox.motif.cases.CaseClassCases Case classes}</li>
   * </ul>
   *
   * <p>In addition the matching case API is extremely extensible. All that is needed to create a
   * new matching case is to implement one of the {@code ExtractorN} (e.g.
   * {@link com.leacox.motif.extractor.Extractor1 Extractor1}) for your type, and then create a new
   * instance of {@code MatchingExtractorN} with the extractor for your type and the args to match
   * on. The easiest way to do this is to create a static method that can be imported like
   * {@link com.leacox.motif.cases.OptionalCases#some(MatchesAny)
   * OptionalCases#some(ArgumentMatcher)}.
   *
   * <p>The following are some basic pattern matching examples:
   * <pre>
   * {@code
   *
   * // Matching on Optional
   * Optional<Person> personOpt = getPerson();
   * match(personOpt)
   *     .when(some(any())).then(person -> doStuff(person))
   *     .when(caseNone()).then(() -> System.out.println("Person not found"))
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
   *             .when(caseTuple2(eq(0), eq(0))).get((x, y) -> "FizzBuzz")
   *             .when(caseTuple2(eq(0), any())).get((x, y) -> "Fizz")
   *             .when(caseTuple2(any(), eq(0))).get((x, y) -> "Buzz")
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
   *    create a reference to the returned object.
   */
  public static <T> FluentMatching<T> match(T value) {
    return new FluentMatching<>(value);
  }
}
