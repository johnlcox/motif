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
import static com.leacox.motif.cases.Case2Cases.case2;

import com.leacox.motif.caseclass.Case2;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */

@RunWith(JunitSuiteRunner.class)
public class CaseClassCasesSpec {
  {
    Case2 cat = Animal.create("Cat", 4);
    NotAnimal rock = NotAnimal.create("Rock", 0);
    describe(
        "the case pattern", it -> {
          it.should(
              "match cat", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, eq("Cat"), eq(4))).get(() -> "Nope")
                    .when(case2(Animal.class, eq("Cat"), eq(4))).get(() -> "Yep")
                    .getMatch();

                expect.that(result).is("Yep");
              });

          it.should(
              "match cat and extract name", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, eq("Cat"), eq(4))).get(() -> "Nope")
                    .when(case2(Animal.class, any(), eq(4))).get(name -> name)
                    .getMatch();

                expect.that(result).is("Cat");
              });

          it.should(
              "match cat and extract legs", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, eq("Cat"), eq(4))).get(() -> "Nope")
                    .when(case2(Animal.class, eq("Cat"), any())).get(legs -> legs.toString())
                    .getMatch();

                expect.that(result).is("4");
              });

          it.should(
              "match cat and extract name and legs", expect -> {
                String result = match(cat)
                    .when(case2(NotAnimal.class, eq("Cat"), eq(4))).get(() -> "Nope")
                    .when(case2(Animal.class, any(), any())).get(
                        (name, legs) -> "(" + name + ", " + legs + ")")
                    .getMatch();

                expect.that(result).is("(Cat, 4)");
              });
        }
    );
  }
}
