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
import static com.leacox.motif.cases.TypeOfCases.typeOf;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class TypeOfCasesSpec {
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
