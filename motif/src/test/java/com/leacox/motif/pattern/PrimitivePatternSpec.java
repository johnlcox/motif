package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.fluent.FluentMotif.match;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseBoolean;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseByte;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseChar;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseDouble;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseFloat;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseInt;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseLong;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseShort;
import static com.leacox.motif.fluent.cases.PrimitiveCases.caseString;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class PrimitivePatternSpec {
  {
    describe(
        "primitive patterns", it -> {
          it.should(
              "match byte", expect -> {
                byte b = 27;

                // No byte literals in Java so you have to cast
                String result = match(b)
                    .when(caseByte((byte) 4)).get(x -> "Nope")
                    .when(caseByte((byte) 27)).get(x -> "27")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(b));
              });

          it.should(
              "match short", expect -> {
                short s = 382;

                // No short literals in Java so you have to cast
                String result = match(s)
                    .when(caseShort((short) 4)).get(x -> "Nope")
                    .when(caseShort((short) 382)).get(x -> "382")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(s));
              });

          it.should(
              "match int", expect -> {
                int i = 2875283;

                String result = match(i)
                    .when(caseInt(4)).get(x -> "Nope")
                    .when(caseInt(2875283)).get(x -> "2875283")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(i));
              });

          it.should(
              "match long", expect -> {
                long l = 82874927472927l;

                String result = match(l)
                    .when(caseLong(4l)).get(x -> "Nope")
                    .when(caseLong(82874927472927l)).get(x -> "82874927472927")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(l));
              });

          it.should(
              "match float", expect -> {
                float f = 382.84782f;

                String result = match(f)
                    .when(caseFloat(4f)).get(x -> "Nope")
                    .when(caseFloat(382.84782f)).get(String::valueOf)
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(f));
              });

          it.should(
              "match double", expect -> {
                double d = 89378393384832.3847849394;

                String result = match(d)
                    .when(caseDouble(4d)).get(x -> "Nope")
                    .when(caseDouble(89378393384832.3847849394)).get(String::valueOf)
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(d));
              });

          it.should(
              "match char", expect -> {
                char c = 'T';

                String result = match(c)
                    .when(caseChar('t')).get(x -> "Nope")
                    .when(caseChar('T')).get(x -> "T")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(c));
              });

          it.should(
              "match String", expect -> {
                String s = "Hello World";

                String result = match(s)
                    .when(caseString("Goodbye World")).get(x -> "Nope")
                    .when(caseString("Hello World")).get(x -> x)
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(s);
              });

          it.should(
              "match boolean true", expect -> {
                boolean t = true;

                String result = match(t)
                    .when(caseBoolean(false)).get(x -> "Nope")
                    .when(caseBoolean(true)).get(x -> "true")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(t));
              });

          it.should(
              "match boolean false", expect -> {
                boolean f = false;

                // No short literals in Java so you have to cast
                String result = match(f)
                    .when(caseBoolean(true)).get(x -> "Nope")
                    .when(caseBoolean(false)).get(x -> "false")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(f));
              });
        });
  }
}
