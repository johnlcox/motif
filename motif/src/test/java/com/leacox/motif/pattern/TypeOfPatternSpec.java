package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.pattern.TypeOfPattern.caseTypeOf;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class TypeOfPatternSpec {
  {
    String myString = "string";
    Double pi = 3.14159;
    OffsetDateTime currentDateTime = OffsetDateTime.now(ZoneOffset.UTC);

    describe(
        "the typeof pattern", it -> {
          it.should(
              "match String", expect -> {
                String result = match(myString).on(
                    caseTypeOf(Double.class, Object::toString),
                    caseTypeOf(OffsetDateTime.class, OffsetDateTime::toString),
                    caseTypeOf(String.class, s -> s)
                );

                expect.that(result).is(myString);
              });

          it.should(
              "match OffsetDateTime", expect -> {
                String result = match(currentDateTime).on(
                    caseTypeOf(Double.class, Object::toString),
                    caseTypeOf(OffsetDateTime.class, OffsetDateTime::toString),
                    caseTypeOf(String.class, s -> s)
                );

                expect.that(result).is(currentDateTime.toString());
              });

          it.should(
              "match Double", expect -> {
                String result = match(pi).on(
                    caseTypeOf(Double.class, Object::toString),
                    caseTypeOf(OffsetDateTime.class, OffsetDateTime::toString),
                    caseTypeOf(String.class, s -> s)
                );

                expect.that(result).is(pi.toString());
              });
        });
  }
}
