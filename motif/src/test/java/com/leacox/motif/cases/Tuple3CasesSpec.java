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
import static com.leacox.motif.MatchesAny.any;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.OptionalCases.some;
import static com.leacox.motif.cases.Tuple2Cases.tuple2;
import static com.leacox.motif.cases.Tuple3Cases.tuple3;

import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.util.Optional;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class Tuple3CasesSpec {
  static {
    describe(
        "the tuple3 pattern", it -> {
          it.should(
              "match exact values", expect -> {
                Tuple3<String, String, String> tuple3 = Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), eq("B"), eq("C"))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "extract first and match second and match third", expect -> {
                Tuple3<String, String, String> tuple3 = Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(any(), eq("B"), eq("C"))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "match first and match second and decompose third to 0", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 =
                    Tuple3.of("A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), eq("B"), some(eq("C")))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match first and match second and decompose third to 1", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 =
                    Tuple3.of("A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), eq("B"), some(any()))).get(c -> c)
                    .getMatch();

                expect.that(result).is("C");
              });

          it.should(
              "match first and match second and decompose third to 2", expect -> {
                Tuple3<String, String, Tuple2<String, String>> tuple3 =
                    Tuple3.of("A", "B", Tuple2.of("C", "D"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), eq("B"), tuple2(any(), any()))).get(
                        (c, d) -> "(" + c + ", " + d + ")")
                    .getMatch();

                expect.that(result).is("(C, D)");
              });

          it.should(
              "match first and match second and decompose third to 3", expect -> {
                Tuple3<String, String, Tuple3<String, String, String>> tuple3 =
                    Tuple3.of("A", "B", Tuple3.of("C", "D", "E"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), eq("B"), tuple3(any(), any(), any())))
                    .get((c, d, e) -> "(" + c + ", " + d + ", " + e + ")")
                    .getMatch();

                expect.that(result).is("(C, D, E)");
              });

          it.should(
              "match first and extract second and match third", expect -> {
                Tuple3<String, String, String> tuple3 = Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), any(), eq("C"))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "match first and extract second and extract third", expect -> {
                Tuple3<String, String, String> tuple3 = Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), any(), any())).get((b, c) -> "(" + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(B, C)");
              });

          it.should(
              "match first and extract second and decompose third to 0", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 = Tuple3.of(
                    "A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), any(), some(eq("C")))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "match first and extract second and decompose third to 1", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 =
                    Tuple3.of("A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), any(), some(any()))).get((b, c) -> b + c)
                    .getMatch();

                expect.that(result).is("BC");
              });

          it.should(
              "match first and extract second and decompose third to 2", expect -> {
                Tuple3<String, String, Tuple2<String, String>> tuple3 =
                    Tuple3.of("A", "B", Tuple2.of("C", "D"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), any(), tuple2(any(), any()))).get((b, c, d) -> b + c + d)
                    .getMatch();

                expect.that(result).is("BCD");
              });

          it.should(
              "match first and decompose second to 0 and match third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(eq("B")), eq("C"))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match first and decompose second to 0 and extract third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(eq("B")), any())).get(c -> c)
                    .getMatch();

                expect.that(result).is("C");
              });

          it.should(
              "match first and decompose second to 0 and decompose third to 0", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(eq("B")), some(eq("C")))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match first and decompose second to 0 and decompose third to 1", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(eq("B")), some(any()))).get(c -> c)
                    .getMatch();

                expect.that(result).is("C");
              });

          it.should(
              "match first and decompose second to 0 and decompose third to 2", expect -> {
                Tuple3<String, Optional<String>, Tuple2<String, String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Tuple2.of("C", "D"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(eq("B")), tuple2(any(), any()))).get((c, d) -> c + d)
                    .getMatch();

                expect.that(result).is("CD");
              });

          it.should(
              "match first and decompose second to 0 and decompose third to 3", expect -> {
                Tuple3<String, Optional<String>, Tuple3<String, String, String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Tuple3.of("C", "D", "E"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(eq("B")), tuple3(any(), any(), any()))).get(
                        (c, d, e) -> c + d + e)
                    .getMatch();

                expect.that(result).is("CDE");
              });

          it.should(
              "match first and decompose second to 1 and match third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(any()), eq("C"))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "match first and decompose second to 1 and extract third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(any()), any())).get((b, c) -> b + c)
                    .getMatch();

                expect.that(result).is("BC");
              });

          it.should(
              "match first and decompose second to 1 and decompose third to 0", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(any()), some(eq("C")))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "match first and decompose second to 1 and decompose third to 1", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(any()), some(any()))).get((b, c) -> b + c)
                    .getMatch();

                expect.that(result).is("BC");
              });

          it.should(
              "match first and decompose second to 1 and decompose third to 2", expect -> {
                Tuple3<String, Optional<String>, Tuple2<String, String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Tuple2.of("C", "D"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), some(any()), tuple2(any(), any())))
                    .get((b, c, d) -> b + c + d)
                    .getMatch();

                expect.that(result).is("BCD");
              });

          //A a, DecomposableMatchBuilder2<B, B1, B2> b, C c
          //A a, DecomposableMatchBuilder2<B, B1, B2> b, MatchesAny c
          //A a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder0<C> c
          //A a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder1<C, C1> c

          it.should(
              "match first and decompose second to 2 and match third", expect -> {
                Tuple3<String, Tuple2<String, String>, String> tuple3 =
                    Tuple3.of("A", Tuple2.of("B", "C"), "D");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), tuple2(any(), any()), eq("D"))).get((b, c) -> b + c)
                    .getMatch();

                expect.that(result).is("BC");
              });

          it.should(
              "match first and decompose second to 2 and extract third", expect -> {
                Tuple3<String, Tuple2<String, String>, String> tuple3 =
                    Tuple3.of("A", Tuple2.of("B", "C"), "D");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), tuple2(any(), any()), any())).get((b, c, d) -> b + c + d)
                    .getMatch();

                expect.that(result).is("BCD");
              });

          it.should(
              "match first and decompose second to 2 and decompose third to 0", expect -> {
                Tuple3<String, Tuple2<String, String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Tuple2.of("B", "C"), Optional.of("D"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), tuple2(any(), any()), some(eq("D")))).get((b, c) -> b + c)
                    .getMatch();

                expect.that(result).is("BC");
              });

          it.should(
              "match first and decompose second to 2 and decompose third to 1", expect -> {
                Tuple3<String, Tuple2<String, String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Tuple2.of("B", "C"), Optional.of("D"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), tuple2(any(), any()), some(any()))).get(
                        (b, c, d) -> b + c + d)
                    .getMatch();

                expect.that(result).is("BCD");
              });

          it.should(
              "match first and decompose second to 3 and match third", expect -> {
                Tuple3<String, Tuple3<String, String, String>, String> tuple3 =
                    Tuple3.of("A", Tuple3.of("B", "C", "D"), "E");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), tuple3(any(), any(), any()), eq("E"))).get((b, c, d) -> b + c + d)
                    .getMatch();

                expect.that(result).is("BCD");
              });

          it.should(
              "match first and decompose second to 3 and decompose third to 0", expect -> {
                Tuple3<String, Tuple3<String, String, String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Tuple3.of("B", "C", "D"), Optional.of("E"));

                String result = match(tuple3)
                    .when(tuple3(eq("A"), tuple3(any(), any(), any()), some(eq("E")))).get(
                        (b, c, d) -> b + c + d)
                    .getMatch();

                expect.that(result).is("BCD");
              });

          it.should(
              "extract first and match second and extract third", expect -> {
                Tuple3<String, String, String> tuple3 =
                    Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(any(), eq("B"), any())).get((a, c) -> a + c)
                    .getMatch();

                expect.that(result).is("AC");
              });

          it.should(
              "extract first and match second and decompose third to 0", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 =
                    Tuple3.of("A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), eq("B"), some(eq("C")))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "extract first and match second and decompose third to 1", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 =
                    Tuple3.of("A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), eq("B"), some(any()))).get((a, c) -> a + c)
                    .getMatch();

                expect.that(result).is("AC");
              });

          it.should(
              "extract first and match second and decompose third to 2", expect -> {
                Tuple3<String, String, Tuple2<String, String>> tuple3 =
                    Tuple3.of("A", "B", Tuple2.of("C", "D"));

                String result = match(tuple3)
                    .when(tuple3(any(), eq("B"), tuple2(any(), any()))).get((a, c, d) -> a + c + d)
                    .getMatch();

                expect.that(result).is("ACD");
              });

          it.should(
              "extract first and extract second and match third", expect -> {
                Tuple3<String, String, String> tuple3 = Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(any(), any(), eq("C"))).get((a, b) -> a + b)
                    .getMatch();

                expect.that(result).is("AB");
              });

          it.should(
              "extract first and extract second and extract third", expect -> {
                Tuple3<String, String, String> tuple3 = Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(any(), any(), any())).get((a, b, c) -> a + b + c)
                    .getMatch();

                expect.that(result).is("ABC");
              });

          it.should(
              "extract first and extract second and decompose third to 0", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 =
                    Tuple3.of("A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), any(), some(eq("C")))).get((a, b) -> a + b)
                    .getMatch();

                expect.that(result).is("AB");
              });

          it.should(
              "extract first and extract second and decompose third to 1", expect -> {
                Tuple3<String, String, Optional<String>> tuple3 =
                    Tuple3.of("A", "B", Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), any(), some(any()))).get((a, b, c) -> a + b + c)
                    .getMatch();

                expect.that(result).is("ABC");
              });

          it.should(
              "extract first and decompose second to 0 and match third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(any(), some(eq("B")), eq("C"))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "extract first and decompose second to 0 and extract third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(any(), some(eq("B")), any())).get((a, c) -> a + c)
                    .getMatch();

                expect.that(result).is("AC");
              });

          it.should(
              "extract first and decompose second to 0 and decompose third to 0", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), some(eq("B")), some(eq("C")))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "extract first and decompose second to 0 and decompose third to 1", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), some(eq("B")), some(any()))).get((a, c) -> a + c)
                    .getMatch();

                expect.that(result).is("AC");
              });

          it.should(
              "extract first and decompose second to 0 and decompose third to 2", expect -> {
                Tuple3<String, Optional<String>, Tuple2<String, String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Tuple2.of("C", "D"));

                String result = match(tuple3)
                    .when(tuple3(any(), some(eq("B")), tuple2(any(), any()))).get(
                        (a, c, d) -> a + c + d)
                    .getMatch();

                expect.that(result).is("ACD");
              });

          it.should(
              "extract first and decompose second to 1 and match third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(any(), some(any()), eq("C"))).get((a, b) -> a + b)
                    .getMatch();

                expect.that(result).is("AB");
              });

          it.should(
              "extract first and decompose second to 1 and extract third", expect -> {
                Tuple3<String, Optional<String>, String> tuple3 =
                    Tuple3.of("A", Optional.of("B"), "C");

                String result = match(tuple3)
                    .when(tuple3(any(), some(any()), any())).get((a, b, c) -> a + b + c)
                    .getMatch();

                expect.that(result).is("ABC");
              });

          it.should(
              "extract first and decompose second to 1 and decompose third to 0", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), some(any()), some(eq("C")))).get((a, b) -> a + b)
                    .getMatch();

                expect.that(result).is("AB");
              });

          it.should(
              "extract first and decompose second to 1 and decompose third to 1", expect -> {
                Tuple3<String, Optional<String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Optional.of("B"), Optional.of("C"));

                String result = match(tuple3)
                    .when(tuple3(any(), some(any()), some(any()))).get((a, b, c) -> a + b + c)
                    .getMatch();

                expect.that(result).is("ABC");
              });

          it.should(
              "extract first and decompose second to 2 and match third", expect -> {
                Tuple3<String, Tuple2<String, String>, String> tuple3 =
                    Tuple3.of("A", Tuple2.of("B", "C"), "D");

                String result = match(tuple3)
                    .when(tuple3(any(), tuple2(any(), any()), eq("D"))).get((a, b, c) -> a + b + c)
                    .getMatch();

                expect.that(result).is("ABC");
              });

          it.should(
              "extract first and decompose second to 2 and decompose third to 0", expect -> {
                Tuple3<String, Tuple2<String, String>, Optional<String>> tuple3 =
                    Tuple3.of("A", Tuple2.of("B", "C"), Optional.of("D"));

                String result = match(tuple3)
                    .when(tuple3(any(), tuple2(any(), any()), some(eq("D")))).get(
                        (a, b, c) -> a + b + c)
                    .getMatch();

                expect.that(result).is("ABC");
              });

          //***
          it.should(
              "match first and match second and extract third", expect -> {
                Tuple3<String, String, String> tuple3 = Tuple3.of("A", "B", "C");

                String result = match(tuple3)
                    .when(tuple3(eq("A"), eq("B"), any())).get(c -> c)
                    .getMatch();

                expect.that(result).is("C");
              });
        }
    );
  }
}
