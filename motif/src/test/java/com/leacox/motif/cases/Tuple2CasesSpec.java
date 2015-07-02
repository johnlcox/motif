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

import com.leacox.motif.tuple.Tuple2;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.util.Optional;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class Tuple2CasesSpec {
  {
    describe(
        "the tuple2 pattern", it -> {
          it.should(
              "match exact values", expect -> {
                Tuple2<String, String> tuple2 = Tuple2.of("A", "B");

                String result = match(tuple2)
                    .when(tuple2("A", "B")).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "extract first and match second", expect -> {
                Tuple2<String, String> tuple2 = Tuple2.of("A", "B");

                String result = match(tuple2)
                    .when(tuple2(any(), "B")).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "match first and extract second", expect -> {
                Tuple2<String, String> tuple2 = Tuple2.of("A", "B");

                String result = match(tuple2)
                    .when(tuple2("A", any())).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "extract first and extract second", expect -> {
                Tuple2<String, String> tuple2 = Tuple2.of("A", "B");

                String result = match(tuple2)
                    .when(tuple2(any(), any())).get((a, b) -> "(" + a + ", " + b + ")")
                    .getMatch();

                expect.that(result).is("(A, B)");
              });

          it.should(
              "match first to 0 and decompose second to 2 and extract 2", expect -> {
                Tuple2<String, Tuple2<String, String>> tuple2 = Tuple2.of("A", Tuple2.of("B", "C"));

                String result = match(tuple2)
                    .when(tuple2("A", tuple2(any(), any()))).get((b, c) -> "(" + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(B, C)");
              });

          it.should(
              "match first to 0 and decompose second to 3 and extract 3", expect -> {
                Tuple2<String, Tuple2<Tuple2<String, String>, String>> tuple2 =
                    Tuple2.of("A", Tuple2.of(Tuple2.of("B", "C"), "D"));

                String result = match(tuple2)
                    .when(tuple2("A", tuple2(tuple2(any(), any()), any()))).get(
                        (b, c, d) -> "(" + b + ", " + c + ", " + d + ")")
                    .getMatch();

                expect.that(result).is("(B, C, D)");
              });

          it.should(
              "decompose first to 0 and match second to 0 and extract none", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(eq("A")), Optional.of("B"))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose first to 0 and match second to 1 and extract to 1", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(eq("A")), any())).get(x -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match first to 0 and decompose second to 0 and extract to 0", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(Optional.of("A"), some(eq("B")))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match first to 0 and decompose second to 1 and extract to 1", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(Optional.of("A"), some(any()))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "decompose first to 0 and decompose second to 0 and extract to 0", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(eq("A")), some(eq("B")))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose first to 0 and decompose second to 1 and extract to 1", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(eq("A")), some(any()))).get(b -> b)
                    .getMatch();

                expect.that(result).is("B");
              });

          it.should(
              "decompose first to 0 and decompose second to 2 and extract to 2", expect -> {
                Tuple2<Optional<String>, Tuple2<String, String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Tuple2.of("B", "C"));

                String result = match(tuple2)
                    .when(tuple2(some(eq("A")), tuple2(any(), any()))).get(
                        (b, c) -> "(" + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(B, C)");
              });

          it.should(
              "decompose first to 0 and decompose second to 3 and extract to 3", expect -> {
                Tuple2<Optional<String>, Tuple2<Tuple2<String, String>, String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Tuple2.of(Tuple2.of("B", "C"), "D"));

                String result = match(tuple2)
                    .when(tuple2(some(eq("A")), tuple2(tuple2(any(), any()), any()))).get(
                        (b, c, d) -> "(" + b + ", " + c + ", " + d + ")")
                    .getMatch();

                expect.that(result).is("(B, C, D)");
              });

          it.should(
              "match first to 1 and decompose second to 0 and extract to 1", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(any(), some(eq("B")))).get(x -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match first to 1 and decompose second to 1 and extract to 2", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(any(), some(any()))).get((aOpt, b) -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match first to 1 and decompose second to 2 and extract to 3", expect -> {
                Tuple2<String, Tuple2<String, String>> tuple2 =
                    Tuple2.of("A", Tuple2.of("B", "C"));

                String result = match(tuple2)
                    .when(tuple2(any(), tuple2(any(), any()))).get(
                        (a, b, c) -> "(" + a + ", " + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(A, B, C)");
              });

          it.should(
              "decompose first to 1 and match second to 0 and extract to 1", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(any()), Optional.of("B"))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "decompose first to 1 and match second to 1 and extract to 2", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(any()), any())).get((a, bOpt) -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "decompose first to 1 and decompose second to 0 and extract to 1", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(any()), some(eq("B")))).get(a -> a)
                    .getMatch();

                expect.that(result).is("A");
              });

          it.should(
              "decompose first to 1 and decompose second to 1 and extract to 2", expect -> {
                Tuple2<Optional<String>, Optional<String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Optional.of("B"));

                String result = match(tuple2)
                    .when(tuple2(some(any()), some(any()))).get((a, b) -> "(" + a + ", " + b + ")")
                    .getMatch();

                expect.that(result).is("(A, B)");
              });

          it.should(
              "decompose first to 1 and decompose second to 2 and extract to 3", expect -> {
                Tuple2<Optional<String>, Tuple2<String, String>> tuple2 =
                    Tuple2.of(Optional.of("A"), Tuple2.of("B", "C"));

                String result = match(tuple2)
                    .when(tuple2(some(any()), tuple2(any(), any()))).get(
                        (a, b, c) -> "(" + a + ", " + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(A, B, C)");
              });

          it.should(
              "decompose first to 2 and match second to 0 and extract to 2", expect -> {
                Tuple2<Tuple2<String, String>, String> tuple2 = Tuple2.of(
                    Tuple2.of("A", "B"), "C");

                String result = match(tuple2)
                    .when(tuple2(tuple2(any(), any()), "C")).get(
                        (a, b) -> "(" + a + ", " + b + ")")
                    .getMatch();

                expect.that(result).is("(A, B)");
              });

          it.should(
              "decompose first to 2 and decompose second to 0 and extract to 2", expect -> {
                Tuple2<Tuple2<String, String>, Optional<String>> tuple2 = Tuple2.of(
                    Tuple2.of("A", "B"), Optional.of("C"));

                String result = match(tuple2)
                    .when(tuple2(tuple2(any(), any()), some(eq("C")))).get(
                        (a, b) -> "(" + a + ", " + b + ")")
                    .getMatch();

                expect.that(result).is("(A, B)");
              });

          it.should(
              "decompose first to 2 and decompose second to 1 and extract to 3", expect -> {
                Tuple2<Tuple2<String, String>, Optional<String>> tuple2 = Tuple2.of(
                    Tuple2.of("A", "B"), Optional.of("C"));

                String result = match(tuple2)
                    .when(tuple2(tuple2(any(), any()), some(any()))).get(
                        (a, b, c) -> "(" + a + ", " + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(A, B, C)");
              });

          it.should(
              "decompose first to 3 and match second to 0 and extract to 3", expect -> {
                Tuple2<Tuple2<Tuple2<String, String>, String>, String> tuple2 = Tuple2.of(
                    Tuple2.of(Tuple2.of("A", "B"), "C"), "D");

                String result = match(tuple2)
                    .when(tuple2(tuple2(tuple2(any(), any()), any()), "D")).get(
                        (a, b, c) -> "(" + a + ", " + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(A, B, C)");
              });

          it.should(
              "decompose first to 3 and decompose second to 0 and extract to 3", expect -> {
                Tuple2<Tuple2<Tuple2<String, String>, String>, Optional<String>> tuple2 = Tuple2.of(
                    Tuple2.of(Tuple2.of("A", "B"), "C"), Optional.of("D"));

                String result = match(tuple2)
                    .when(tuple2(tuple2(tuple2(any(), any()), any()), some(eq("D")))).get(
                        (a, b, c) -> "(" + a + ", " + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(A, B, C)");
              });
        }
    );
  }
}
