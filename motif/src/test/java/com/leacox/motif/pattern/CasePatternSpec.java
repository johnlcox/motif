package com.leacox.motif.pattern;

/**
 * @author John Leacox
 */

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.fluent.FluentMotif.match;
import static com.leacox.motif.fluent.cases.CaseClassCases.case2;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

@RunWith(JunitSuiteRunner.class)
public class CasePatternSpec {
  {
    Animal cat = Animal.create("Cat", 4);
    NotAnimal rock = NotAnimal.create("Rock", 0);
    describe(
        "the case pattern", it -> it.should(
            "match cat", expect -> {
              String result = match(cat)
                  .when(case2(NotAnimal.class, eq("Cat"), eq(4))).get(
                      (name, legs) -> "(" + name + ", " + legs + ")")
                  .when(case2(Animal.class, eq("Cat"), eq(4))).get(
                      (name, legs) -> "(" + name + ", " + legs + ")")
                  .getMatch();

              expect.that(result).is("(Cat, 4)");
            }));
  }
}
