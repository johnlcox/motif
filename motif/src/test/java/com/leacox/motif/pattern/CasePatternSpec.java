package com.leacox.motif.pattern;

/**
 * @author John Leacox
 */

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.pattern.Animal.caseAnimal;

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
                  .when(caseAnimal("Cat", 4, (name, legs) -> "(" + name + ", " + legs + ")"))
                  .get();

              expect.that(result).is("(Cat, 4)");
            }));
  }
}
