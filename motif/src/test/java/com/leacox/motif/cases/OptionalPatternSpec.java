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
public class OptionalPatternSpec {
  {
    Optional<String> none = Optional.empty();
    Optional<String> some = Optional.of("a string");
    describe(
        "the optional pattern", it -> {
          it.should(
              "handle none", expect -> {
                String result = match(none)
                    .when(none()).get(() -> "None")
                    .getMatch();

                expect.that(result).is("None");
              });

          it.should(
              "consume none", expect -> {
                Consuming consuming = new Consuming();

                match(none)
                    .when(none()).then(() -> consuming.consume("None"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("None");
              });

          it.should(
              "handle some", expect -> {
                String result = match(some)
                    .when(some("not a string?")).get(() -> "What?")
                    .when(some("a string")).get(() -> "Found it")
                    .when(some(any())).get(a -> a)
                    .when(none()).get(() -> "hi")
                    .getMatch();

                expect.that(result).is("Found it");
              });

          it.should(
              "handle some with exact match", expect -> {
                String result = match(some)
                    .when(none()).get(() -> "None")
                    .when(some("Not a string?")).get(() -> "What?")
                    .when(some("a string")).get(() -> "Found it")
                    .getMatch();

                expect.that(result).is("Found it");
              });

          it.should(
              "consume some", expect -> {
                Consuming consuming = new Consuming();

                match(some)
                    .when(none()).then(() -> consuming.consume("None"))
                    .when(some(any())).then(consuming::consume)
                    .doMatch();

                expect.that(consuming.getConsumed()).is(some.get());
              });

          it.should(
              "handle orElse", expect -> {
                String result = match(some)
                    .when(none()).get(() -> "None")
                    .orElse("orElse")
                    .getMatch();

                expect.that(result).is("orElse");
              });

          it.should(
              "consume otherwise", expect -> {
                Consuming consuming = new Consuming();

                match(some)
                    .when(none()).then(() -> consuming.consume("None"))
                    .orElse(t -> consuming.consume("otherwise"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("otherwise");
              });

          it.should(
              "decompose to one", expect -> {
                Optional<Optional<String>> optOpt = Optional.of(Optional.of("A String"));

                String result = match(optOpt)
                    .when(some(none())).get(() -> "None")
                    .when(some(some("A String"))).get(() -> "Some")
                    .getMatch();

                expect.that(result).is("Some");
              });

          it.should(
              "decompose and extract to one", expect -> {
                Optional<Optional<String>> optOpt = Optional.of(Optional.of("A String"));

                String result = match(optOpt)
                    .when(some(none())).get(() -> "None")
                    .when(some(some(any()))).get(x -> x)
                    .getMatch();

                expect.that(result).is("A String");
              });

          it.should(
              "decompose to two", expect -> {
                Optional<Tuple2<String, String>> tuple2Opt = Optional.of(Tuple2.of("A", "B"));

                String result = match(tuple2Opt)
                    .when(some(tuple2("C", "D"))).get(() -> "Nope1")
                    .when(some(tuple2("A", "C"))).get(() -> "Nope2")
                    .when(some(tuple2("D", "B"))).get(() -> "Nope3")
                    .when(some(tuple2("A", "B"))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose to two and extract first", expect -> {
                Optional<Tuple2<String, String>> tuple2Opt = Optional.of(Tuple2.of("A", "B"));

                String result = match(tuple2Opt)
                    .when(some(tuple2("C", "D"))).get(() -> "Nope1")
                    .when(some(tuple2("A", "C"))).get(() -> "Nope2")
                    .when(some(tuple2("D", "B"))).get(() -> "Nope3")
                    .when(some(tuple2(any(), "B"))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "decompose to two and extract second", expect -> {
                Optional<Tuple2<String, String>> tuple2Opt = Optional.of(Tuple2.of("A", "B"));

                String result = match(tuple2Opt)
                    .when(some(tuple2("C", "D"))).get(() -> "Nope")
                    .when(some(tuple2("A", "C"))).get(() -> "Nope")
                    .when(some(tuple2("D", "B"))).get(() -> "Nope")
                    .when(some(tuple2("A", any()))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "decompose to two and extract both", expect -> {
                Optional<Tuple2<String, String>> tuple2Opt = Optional.of(Tuple2.of("A", "B"));

                String result = match(tuple2Opt)
                    .when(some(tuple2("C", "D"))).get(() -> "Nope")
                    .when(some(tuple2("A", "C"))).get(() -> "Nope")
                    .when(some(tuple2("D", "B"))).get(() -> "Nope")
                    .when(some(tuple2(any(), any()))).get((a, b) -> "(" + a + ", " + b + ")")
                    .getMatch();

                expect.that(result).is("(A, B)");
              });
        }
    );
  }
}
