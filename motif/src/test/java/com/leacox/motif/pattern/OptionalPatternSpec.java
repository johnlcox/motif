package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.fluent.FluentMotif.match;
import static com.leacox.motif.fluent.cases.OptionalCases.caseNone;
import static com.leacox.motif.fluent.cases.OptionalCases.caseSome;
import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.anyString;

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
                    .when(caseNone()).get(() -> "None")
                    .getMatch();

                expect.that(result).is("None");
              });

          it.should(
              "consume none", expect -> {
                Consuming consuming = new Consuming();

                match(none)
                    .when(caseNone()).then(() -> consuming.consume("None"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("None");
              });

          it.should(
              "handle some", expect -> {
                String result = match(some)
                    .when(caseSome("not a string?")).get(a -> "What?")
                    .when(caseSome("a string")).get(a -> "Found it")
                    .when(caseSome(anyString())).get(a -> a)
                    .when(caseNone()).get(() -> "hi")
                    .getMatch();

                expect.that(result).is("Found it");
              });

          it.should(
              "handle some with exact match", expect -> {
                String result = match(some)
                    .when(caseNone()).get(() -> "None")
                    .when(caseSome("Not a string?")).get(t -> "What?")
                    .when(caseSome("a string")).get(t -> "Found it")
                    .getMatch();

                expect.that(result).is("Found it");
              });

          it.should(
              "consume some", expect -> {
                Consuming consuming = new Consuming();

                match(some)
                    .when(caseNone()).then(() -> consuming.consume("None"))
                    .when(caseSome(any())).then(consuming::consume)
                    .doMatch();

                expect.that(consuming.getConsumed()).is(some.get());
              });

          it.should(
              "handle orElse", expect -> {
                String result = match(some)
                    .when(caseNone()).get(() -> "None")
                    .orElse("orElse")
                    .getMatch();

                expect.that(result).is("orElse");
              });

          it.should(
              "consume otherwise", expect -> {
                Consuming consuming = new Consuming();

                match(some)
                    .when(caseNone()).then(() -> consuming.consume("None"))
                    .orElse(t -> consuming.consume("otherwise"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("otherwise");
              });
        });
  }
}
