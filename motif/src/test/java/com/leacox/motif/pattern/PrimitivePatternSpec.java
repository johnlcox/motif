package com.leacox.motif.pattern;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.pattern.OrElsePattern.orElse;
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
                String result = match(b).on(
                    caseByte((byte) 4, x -> "Nope"),
                    caseByte((byte) 27, x -> "27"),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(b));
              });

          it.should(
              "match short", expect -> {
                short s = 382;

                // No short literals in Java so you have to cast
                String result = match(s).on(
                    caseShort((short) 4, x -> "Nope"),
                    caseShort((short) 382, x -> "382"),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(s));
              });

          it.should(
              "match int", expect -> {
                int i = 2875283;

                String result = match(i).on(
                    caseInt(4, x -> "Nope"),
                    caseInt(2875283, x -> "2875283"),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(i));
              });

          it.should(
              "match long", expect -> {
                long l = 82874927472927l;

                String result = match(l).on(
                    caseLong(4l, x -> "Nope"),
                    caseLong(82874927472927l, x -> "82874927472927"),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(l));
              });

          it.should(
              "match float", expect -> {
                float f = 382.84782f;

                String result = match(f).on(
                    caseFloat(4f, x -> "Nope"),
                    caseFloat(382.84782f, String::valueOf),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(f));
              });

          it.should(
              "match double", expect -> {
                double d = 89378393384832.3847849394;

                String result = match(d).on(
                    caseDouble(4d, x -> "Nope"),
                    caseDouble(89378393384832.3847849394, String::valueOf),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(d));
              });

          it.should(
              "match char", expect -> {
                char c = 'T';

                String result = match(c).on(
                    caseChar('t', x -> "Nope"),
                    caseChar('T', x -> "T"),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(c));
              });

          it.should(
              "match String", expect -> {
                String s = "Hello World";

                String result = match(s).on(
                    caseString("Goodbye World", x -> "Nope"),
                    caseString("Hello World", x -> x),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(s);
              });

          it.should(
              "match boolean true", expect -> {
                boolean t = true;

                String result = match(t).on(
                    caseBoolean(false, x -> "Nope"),
                    caseBoolean(true, x -> "true"),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(t));
              });

          it.should(
              "match boolean false", expect -> {
                boolean f = false;

                // No short literals in Java so you have to cast
                String result = match(f).on(
                    caseBoolean(true, x -> "Nope"),
                    caseBoolean(false, x -> "false"),
                    orElse(x -> "orElse")
                );

                expect.that(result).is(String.valueOf(f));
              });
        });
  }
}
