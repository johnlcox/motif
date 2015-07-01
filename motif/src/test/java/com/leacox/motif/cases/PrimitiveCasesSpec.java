/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leacox.motif.cases;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.PrimitiveCases.caseBoolean;
import static com.leacox.motif.cases.PrimitiveCases.caseByte;
import static com.leacox.motif.cases.PrimitiveCases.caseChar;
import static com.leacox.motif.cases.PrimitiveCases.caseDouble;
import static com.leacox.motif.cases.PrimitiveCases.caseFloat;
import static com.leacox.motif.cases.PrimitiveCases.caseInt;
import static com.leacox.motif.cases.PrimitiveCases.caseLong;
import static com.leacox.motif.cases.PrimitiveCases.caseShort;
import static com.leacox.motif.cases.PrimitiveCases.caseString;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class PrimitiveCasesSpec {
  {
    describe(
        "primitive patterns", it -> {
          it.should(
              "match byte", expect -> {
                byte b = 27;

                // No byte literals in Java so you have to cast
                String result = match(b)
                    .when(caseByte((byte) 4)).get(() -> "Nope")
                    .when(caseByte((byte) 27)).get(() -> "27")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(b));
              });

          it.should(
              "match short", expect -> {
                short s = 382;

                // No short literals in Java so you have to cast
                String result = match(s)
                    .when(caseShort((short) 4)).get(() -> "Nope")
                    .when(caseShort((short) 382)).get(() -> "382")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(s));
              });

          it.should(
              "match int", expect -> {
                int i = 2875283;

                String result = match(i)
                    .when(caseInt(4)).get(() -> "Nope")
                    .when(caseInt(2875283)).get(() -> "2875283")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(i));
              });

          it.should(
              "match long", expect -> {
                long l = 82874927472927l;

                String result = match(l)
                    .when(caseLong(4l)).get(() -> "Nope")
                    .when(caseLong(82874927472927l)).get(() -> "82874927472927")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(l));
              });

          it.should(
              "match float", expect -> {
                float f = 382.84782f;

                String result = match(f)
                    .when(caseFloat(4f)).get(() -> "Nope")
                    .when(caseFloat(382.84782f)).get(() -> "Yep")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match double", expect -> {
                double d = 89378393384832.3847849394;

                String result = match(d)
                    .when(caseDouble(4d)).get(() -> "Nope")
                    .when(caseDouble(89378393384832.3847849394)).get(() -> "Yep")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match char", expect -> {
                char c = 'T';

                String result = match(c)
                    .when(caseChar('t')).get(() -> "Nope")
                    .when(caseChar('T')).get(() -> "T")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(c));
              });

          it.should(
              "match String", expect -> {
                String s = "Hello World";

                String result = match(s)
                    .when(caseString("Goodbye World")).get(() -> "Nope")
                    .when(caseString("Hello World")).get(() -> "Hello World")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(s);
              });

          it.should(
              "match boolean true", expect -> {
                boolean t = true;

                String result = match(t)
                    .when(caseBoolean(false)).get(() -> "Nope")
                    .when(caseBoolean(true)).get(() -> "true")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(t));
              });

          it.should(
              "match boolean false", expect -> {
                boolean f = false;

                // No short literals in Java so you have to cast
                String result = match(f)
                    .when(caseBoolean(true)).get(() -> "Nope")
                    .when(caseBoolean(false)).get(() -> "false")
                    .orElse(x -> "orElse")
                    .getMatch();

                expect.that(result).is(String.valueOf(f));
              });
        });
  }
}
