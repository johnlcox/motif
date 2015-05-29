package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.pattern.PrimitivePattern.caseBoolean;
import static com.leacox.motif.pattern.PrimitivePattern.caseByte;
import static com.leacox.motif.pattern.PrimitivePattern.caseChar;
import static com.leacox.motif.pattern.PrimitivePattern.caseDouble;
import static com.leacox.motif.pattern.PrimitivePattern.caseFloat;
import static com.leacox.motif.pattern.PrimitivePattern.caseInt;
import static com.leacox.motif.pattern.PrimitivePattern.caseLong;
import static com.leacox.motif.pattern.PrimitivePattern.caseShort;
import static com.leacox.motif.pattern.PrimitivePattern.caseString;

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
                    .when(caseByte((byte) 4, x -> "Nope"))
                    .when(caseByte((byte) 27, x -> "27"))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(b));
              });

          it.should(
              "match short", expect -> {
                short s = 382;

                // No short literals in Java so you have to cast
                String result = match(s)
                    .when(caseShort((short) 4, x -> "Nope"))
                    .when(caseShort((short) 382, x -> "382"))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(s));
              });

          it.should(
              "match int", expect -> {
                int i = 2875283;

                String result = match(i)
                    .when(caseInt(4, x -> "Nope"))
                    .when(caseInt(2875283, x -> "2875283"))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(i));
              });

          it.should(
              "match long", expect -> {
                long l = 82874927472927l;

                String result = match(l)
                    .when(caseLong(4l, x -> "Nope"))
                    .when(caseLong(82874927472927l, x -> "82874927472927"))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(l));
              });

          it.should(
              "match float", expect -> {
                float f = 382.84782f;

                String result = match(f)
                    .when(caseFloat(4f, x -> "Nope"))
                    .when(caseFloat(382.84782f, String::valueOf))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(f));
              });

          it.should(
              "match double", expect -> {
                double d = 89378393384832.3847849394;

                String result = match(d)
                    .when(caseDouble(4d, x -> "Nope"))
                    .when(caseDouble(89378393384832.3847849394, String::valueOf))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(d));
              });

          it.should(
              "match char", expect -> {
                char c = 'T';

                String result = match(c)
                    .when(caseChar('t', x -> "Nope"))
                    .when(caseChar('T', x -> "T"))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(c));
              });

          it.should(
              "match String", expect -> {
                String s = "Hello World";

                String result = match(s)
                    .when(caseString("Goodbye World", x -> "Nope"))
                    .when(caseString("Hello World", x -> x))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(s);
              });

          it.should(
              "match boolean true", expect -> {
                boolean t = true;

                String result = match(t)
                    .when(caseBoolean(false, x -> "Nope"))
                    .when(caseBoolean(true, x -> "true"))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(t));
              });

          it.should(
              "match boolean false", expect -> {
                boolean f = false;

                // No short literals in Java so you have to cast
                String result = match(f)
                    .when(caseBoolean(true, x -> "Nope"))
                    .when(caseBoolean(false, x -> "false"))
                    .orElse(() -> "orElse")
                    .get();

                expect.that(result).is(String.valueOf(f));
              });
        });
  }
}
