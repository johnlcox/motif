package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.CaseThatCases.caseEq;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class CaseThatCasesSpec {
  {
    Object pi = Math.PI;

    describe(
        "the caseThat pattern", it -> {
          it.should(
              "match pi", expect -> {
                String result = match(pi)
                    .when(caseEq(42)).get(t -> "a magic no.")
                    .when(caseEq("Hello!")).get(t -> "a greet")
                    .when(caseEq(Math.PI)).get(t -> "another magic no.")
                    .orElse("something else")
                    .getMatch();

                expect.that(result).is("another magic no.");
              }
          );
        }
    );
  }
}
