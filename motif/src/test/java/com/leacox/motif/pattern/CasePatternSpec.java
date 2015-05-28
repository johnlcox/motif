package com.leacox.motif.pattern;

/**
 * @author John Leacox
 */

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.Motif.matchInt;
import static com.leacox.motif.pattern.Animal.caseAnimal;
import static com.leacox.motif.pattern.PrimitivePattern.caseInt;
import static com.leacox.motif.pattern.PrimitivePattern.caseIntBoxing;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.util.Random;
import java.util.stream.IntStream;

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

              Random random = new Random();

              long start = System.currentTimeMillis();

              IntStream.range(0, 1000000).forEach(
                  x -> {
                    int i = random.nextInt();

                    String intResult = matchInt(i)
                        .when(caseInt(4, j -> "It's " + 4))
                        .when(caseInt(i, j -> "It's " + j))
                        .get();

                    if (intResult.equals("It's 4")) {
                      System.out.println(intResult);
                    }
                  });

              long end = System.currentTimeMillis();
              System.out.println("Total time taken: " + (end - start) + "ms");

              start = System.currentTimeMillis();

              IntStream.range(0, 1000000).forEach(
                  x -> {
                    int i = random.nextInt();

                    String intResult = match(i)
                        .when(caseIntBoxing(4, j -> "It's " + 4))
                        .when(caseIntBoxing(i, j -> "It's " + j))
                        .get();

                    if (intResult.equals("It's 4")) {
                      System.out.println(intResult);
                    }
                  });

              end = System.currentTimeMillis();
              System.out.println("Total time taken for boxing: " + (end - start) + "ms");

              expect.that(result).is("(Cat, 4)");
            }));
  }
}
