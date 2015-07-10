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

package com.leacox.motif.tuple;

import static com.insightfullogic.lambdabehave.Suite.describe;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;

import org.junit.runner.RunWith;

/**
 * @author John Leacox
 */
@RunWith(JunitSuiteRunner.class)
public class Tuple2Spec {
  {
    describe(
        "Tuple2", it -> it.should(
            "put tuple values inside () for toString", expect -> {
              Tuple2<String, Integer> tuple2 = Tuple2.of("AString", 83);

              expect.that(tuple2.toString()).is(("(AString,83)"));
            })
    );
  }
}
