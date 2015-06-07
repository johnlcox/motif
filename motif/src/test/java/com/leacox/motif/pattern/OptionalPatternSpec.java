package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.fluent.cases.OptionalCases.caseNone2;
import static com.leacox.motif.fluent.cases.OptionalCases.caseSome2;
import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.pattern.OptionalPattern.caseNone;
import static com.leacox.motif.pattern.OptionalPattern.caseSome;
import static com.leacox.motif.pattern.OptionalPattern.cazeNone;
import static com.leacox.motif.pattern.OrElsePattern.orElse;
import static com.leacox.motif.pattern.OrElsePattern.otherwise;

import com.leacox.motif.fluent.FluentMotif;

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
                String result = match(none).on(
                    caseNone(() -> "None")
                );

                expect.that(result).is("None");
              });

          it.should(
              "consume none", expect -> {
                Consuming consuming = new Consuming();

                match(none).on(
                    cazeNone(() -> consuming.consume("None"))
                );

                expect.that(consuming.getConsumed()).is("None");
              });

          it.should(
              "handle some", expect -> {
                String result = FluentMotif.match(some)
                    .when(caseSome2("not a string?")).get(a -> "What?")
                    .when(caseSome2("a string")).get(a -> "Found it")
                    .when(caseSome2(any())).get(a -> a)
                    .when(caseNone2()).get(() -> "hi")
                    .getMatch();

                expect.that(result).is("Found it");
              });

          it.should(
              "handle some with exact match", expect -> {
                String result = match(some).on(
                    caseNone(() -> "None"),
                    caseSome("Not a string?", t -> "What?"),
                    caseSome("a string", t -> "Found it")
                );

                expect.that(result).is("Found it");
              });

          it.should(
              "consume some", expect -> {
                Consuming consuming = new Consuming();

                FluentMotif.match(some)
                    .when(caseNone2()).then(() -> consuming.consume("None"))
                    .when(caseSome2(any())).then(consuming::consume)
                    .doMatch();

                expect.that(consuming.getConsumed()).is(some.get());
              });

          it.should(
              "handle orElse", expect -> {
                String result = match(some).on(
                    caseNone(() -> "None"),
                    orElse("orElse")
                );

                expect.that(result).is("orElse");
              });

          it.should(
              "consume otherwise", expect -> {
                Consuming consuming = new Consuming();

                match(some).on(
                    cazeNone(() -> consuming.consume("None")),
                    otherwise(t -> consuming.consume("otherwise"))
                );

                expect.that(consuming.getConsumed()).is("otherwise");
              });
        });
  }
}
