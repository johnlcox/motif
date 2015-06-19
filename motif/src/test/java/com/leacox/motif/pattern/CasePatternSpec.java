package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.CaseClassCases.case2;
import static com.leacox.motif.decomposition.MatchesAny.any;

import com.leacox.motif.caseclass.Case2;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */

@RunWith(JunitSuiteRunner.class)
public class CasePatternSpec {
  {
    Case2 cat = Animal.create("Cat", 4);
    NotAnimal rock = NotAnimal.create("Rock", 0);
    describe(
        "the case pattern", it -> {
          it.should(
              "match cat", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, "Cat", 4)).get(() -> "Nope")
                    .when(case2(Animal.class, "Cat", 4)).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match cat and extract name", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, "Cat", 4)).get(() -> "Nope")
                    .when(case2(Animal.class, any(), 4)).get(name -> name)
                    .getMatch();

                expect.that(result).is("Cat");
              });

          it.should(
              "match cat and extract legs", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, "Cat", 4)).get(() -> "Nope")
                    .when(case2(Animal.class, "Cat", any())).get(legs -> legs.toString())
                    .getMatch();

                expect.that(result).is("4");
              });

          it.should(
              "match cat and extract name and legs", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, "Cat", 4)).get(() -> "Nope")
                    .when(case2(Animal.class, any(), any())).get(
                        (name, legs) -> "(" + name + ", " + legs + ")")
                    .getMatch();

                expect.that(result).is("(Cat, 4)");
              });
        }
    );
  }
}
