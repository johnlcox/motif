package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.pattern.OptionalPattern.caseNone;
import static com.leacox.motif.pattern.OptionalPattern.caseSome;
import static com.leacox.motif.pattern.OrElsePattern.orElse;

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
              "handle some", expect -> {
                String result = match(some).on(
                    caseNone(() -> "None"),
                    caseSome((String a) -> a)
                );

                expect.that(result).is("a string");
              });

          it.should(
              "handle orElse", expect -> {
                String result = match(some).on(
                    caseNone(() -> "None"),
                    orElse("orElse")
                );

                expect.that(result).is("orElse");
              }
          );
        });
  }
}
