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
import static com.leacox.motif.cases.ListConsCases.nil;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class ListConsCasesSpec {
  {
    List<String> emptyList = new ArrayList<>();
    List<String> oneItemList = new ArrayList<>();
    oneItemList.add("one");
    List<String> twoItemList = new ArrayList<>();
    twoItemList.add("one");
    twoItemList.add("two");

    describe(
        "the list pattern", it -> {
          it.should(
              "match empty list", expect -> {
                String result = match(emptyList)
                    .when(nil()).get(() -> "Nil")
                    .orElse("orElse")
                    .getMatch();

                expect.that(result).is("Nil");
              });

          it.should(
              "match empty list and consume right side", expect -> {
                Consuming consuming = new Consuming();

                match(emptyList)
                    .when(nil()).then(() -> consuming.consume("nil"))
                    .orElse(x -> consuming.consume("orElse"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("nil");
              });

          it.should(
              "match one item list", expect -> {
                String result = match(oneItemList)
                    .when(nil()).get(() -> "Nil")
                    .when(headNil(any())).get(s -> s)
                    .getMatch();

                expect.that(result).is("one");
              });

          it.should(
              "match one item list and consume right side", expect -> {
                Consuming consuming = new Consuming();

                match(oneItemList)
                    .when(nil()).then(() -> consuming.consume("nil"))
                    .when(headNil(any())).then(consuming::consume)
                    .orElse(() -> consuming.consume("Nope"))
                    .doMatch();

                expect.that(consuming.getConsumed()).is("one");
              });

          it.should(
              "match multi-item list", expect -> {
                String result = match(twoItemList)
                    .when(nil()).get(() -> "Nil")
                    .when(headNil(any())).get((x) -> x)
                    .when(headTail(any(), any())).get((x, xs) -> "head: " + x + " tail: " + xs)
                    .getMatch();

                expect.that(result).is("head: one tail: [two]");
              });

          it.should(
              "match multi-item list and consume right side", expect -> {
                Consuming consuming = new Consuming();

                // TODO: Why is intellij formatting consuming lambda continuation indents this way?
                match(twoItemList)
                    .when(nil()).then(() -> consuming.consume("nil"))
                    .when(headNil(any())).then(consuming::consume)
                    .when(headTail(any(), any())).then(
                    (x, xs) ->
                        consuming.consume("head: " + x + " tail: " + xs))
                    .doMatch();
              });
        }
    );
  }
}
