package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.TypeOfCases.typeOf;

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
                String result = match(myString)
                    .when(typeOf(Double.class)).get(Object::toString)
                    .when(typeOf(OffsetDateTime.class)).get(OffsetDateTime::toString)
                    .when(typeOf(String.class)).get(s -> s)
                    .getMatch();

                expect.that(result).is(myString);
              });

          it.should(
              "match OffsetDateTime", expect -> {
                String result = match(currentDateTime)
                    .when(typeOf(Double.class)).get(Object::toString)
                    .when(typeOf(OffsetDateTime.class)).get(OffsetDateTime::toString)
                    .when(typeOf(String.class)).get(s -> s)
                    .getMatch();

                expect.that(result).is(currentDateTime.toString());
              });

          it.should(
              "match Double", expect -> {
                String result = match(pi)
                    .when(typeOf(Double.class)).get(Object::toString)
                    .when(typeOf(OffsetDateTime.class)).get(OffsetDateTime::toString)
                    .when(typeOf(String.class)).get(s -> s)
                    .getMatch();

                expect.that(result).is(pi.toString());
              });
        });
  }
}
