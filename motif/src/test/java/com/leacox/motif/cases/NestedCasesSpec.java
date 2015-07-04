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
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.ListConsCases.headNil;
import static com.leacox.motif.cases.ListConsCases.headTail;
import static com.leacox.motif.cases.OptionalCases.some;
import static com.leacox.motif.cases.Tuple2Cases.tuple2;
import static com.leacox.motif.cases.TypeOfCases.typeOf;

import com.leacox.motif.extract.util.Lists;
import com.leacox.motif.tuple.Tuple2;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class NestedCasesSpec {
  {
    describe(
        "nested cases", it -> {
          it.should(
              "match nested headTail lists", expect -> {
                Optional<List<String>> opt = Optional.of(Lists.of("a", "b"));

                String result = match(opt)
                    .when(some(headTail(any(), any()))).get((head, tail) -> head + tail.get(0))
                    .getMatch();

                expect.that(result).is("ab");
              });

          it.should(
              "match nested Optionals", expect -> {
                Optional<Optional<Optional<String>>> opt =
                    Optional.of(Optional.of(Optional.of("a")));

                String result = match(opt)
                    .when(some(some(some(any())))).get(a -> a)
                    .getMatch();

                expect.that(result).is("a");
              });

          it.should(
              "match tuple2 of Lists", expect -> {
                Tuple2<Tuple2<String, String>, List<String>> tuple2 = Tuple2.of(
                    Tuple2.of("A", "B"), Lists.of("C"));

                String result = match(tuple2)
                    .when(tuple2(tuple2(any(), any()), headNil(any()))).get(
                        (a, b, c) -> "(" + a + ", " + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(A, B, C)");
              });

          it.should(
              "match tuple2 of Optionals", expect -> {
                Tuple2<Tuple2<String, String>, Optional<String>> tuple2 = Tuple2.of(
                    Tuple2.of("A", "B"), Optional.of("C"));

                String result = match(tuple2)
                    .when(tuple2(tuple2(any(), any()), some(any()))).get(
                        (a, b, c) -> "(" + a + ", " + b + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(A, B, C)");
              });

          it.should(
              "match List of Tuple2", expect -> {
                List<Tuple2<String, String>> list =
                    Lists.of(Tuple2.of("a", "b"), Tuple2.of("c", "d"));

                String result = match(list)
                    .when(headTail(tuple2(any(), any()), any())).get(
                        (a, b, tail) -> "(" + a + ", " + b + ", " + tail.get(0).first() + ")")
                    .getMatch();

                expect.that(result).is("(a, b, c)");
              });

          it.should(
              "match List of Lists", expect -> {
                List<List<String>> list = Lists.of(Lists.of("a", "b"), Lists.of("c"));

                String result = match(list)
                    .when(headTail(headTail(any(), any()), headNil(headNil(any())))).get(
                        (a, b, c) -> "(" + a + ", " + b.get(0) + ", " + c + ")")
                    .getMatch();

                expect.that(result).is("(a, b, c)");
              });

          it.should(
              "match nested typeOf", expect -> {
                Tuple2<Object, Object> tuple2 = Tuple2.of((Object) 2, (Object) 4);

                String result = match(tuple2)
                    .when(tuple2(typeOf(Integer.class), typeOf(Integer.class))).get(
                        (a, b) -> String.valueOf(a))
                    .getMatch();

                expect.that(result).is("2");
              });
        });
  }
}