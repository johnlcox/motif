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
package com.leacox.motif.hamcrest;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.hamcrest.CaseThatCases.caseEq;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class CaseThatCasesSpec {
  {
    Object pi = Math.PI;

    describe(
        "the caseThat pattern", it -> {
          it.should(
              "match pi", expect -> {
                String result = match(pi)
                    .when(caseEq(42)).get(t -> "a magic no.")
                    .when(caseEq("Hello!")).get(t -> "a greet")
                    .when(caseEq(Math.PI)).get(t -> "another magic no.")
                    .orElse("something else")
                    .getMatch();

                expect.that(result).is("another magic no.");
              }
          );
        }
    );
  }
}
