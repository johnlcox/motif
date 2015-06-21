package com.leacox.motif.cases;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.MatchesAny.any;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.OptionalCases.none;
import static com.leacox.motif.cases.OptionalCases.some;
import static com.leacox.motif.cases.Tuple2Cases.tuple2;

import com.leacox.motif.tuple.Tuple2;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.util.Optional;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class Tuple2CasesSpec {
  {
    describe(
        "the tuple2 pattern", it -> {
          it.should(
              "decompose second, match first and extract down to none", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(Optional.of("A"), some("B"))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose first, match second and extract down to none", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some("A"), Optional.of("B"))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose second and extract down to first only", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(any(), some("B"))).get(x -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose first and extract down to second only", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some("A"), any())).get(x -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose first and extract decomposed first and un-decomposed second", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(any(), some(any()))).get((aOpt, b) -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose second and extract undecomposed first and decomposed second", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some(any()), any())).get((a, bOpt) -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose two", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some("A"), some("B"))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose second, match first and extract second", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(Optional.of("A"), some(any()))).get(b -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose first, match second and extract first", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some(any()), Optional.of("B"))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "decompose two and extract first", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some(any()), some("B"))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "decompose two and extract second", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some("A"), some(any()))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "decompose two and extract both", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some("A"), none())).get(() -> "Nope")
                    .when(tuple2(none(), some("B"))).get(() -> "Nope")
                    .when(tuple2(some(any()), some(any()))).get((a, b) -> "(" + a + ", " + b + ")")
                    .getMatch();

                expect.that(result).is("(A, B)");
              });
        }
    );
    //Optional<Tuple2<String, String>> doubleNested = Optional.of(

    //    Tuple2.of("nest1", "nest2"));
    //
    //String result2 = match(doubleNested)
    //    .when(some(tuple2(any(), any()))).get((x, y) -> y)
    //    .getMatch();
    //
    //expect.that(result2).is("nest2");
    //
    //expect.that(result).is("nested");
    //
    ////Tuple2<String, Tuple2<String, String>> tupled = Tuple2.of("A", Tuple2.of("C", "D"));
    //
    //Tuple2<Tuple2<String, String>, Tuple2<String, String>> tupled = Tuple2.of(
    //    Tuple2.of("A", "B"), Tuple2.of("C", "D"));
    //
    //String result3 = match(tupled)
    //    .when(tuple2(tuple2(any(), "B"), tuple2("C", any()))).get(
    //        (a, d) -> "(" + a + ", " + d + ")")
    //    .getMatch();
    //
    //expect.that(result3).is("(A, D)");
    //
    //String result4 = match(tupled)
    //    .when(tuple2(tuple2("A", "B"), tuple2(any(), "D"))).get(c -> c)
    //    .getMatch();
    //
    //expect.that(result4).is("C");
  }
}
