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
package com.leacox.motif.example;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.hamcrest.CaseThatCases.caseEq;

/**
 * @author John Leacox
 */
public class CaseThatExample {
  public static void main(String[] args) {
    new CaseThatExample().run();
  }

  public void run() {
    Object pi = Math.PI;

    String result = match(pi)
        .when(caseEq(42)).get(t -> "a magic no.")
        .when(caseEq("Hello!")).get(t -> "a greet")
        .when(caseEq(Math.PI)).get(t -> "another magic no.")
        .orElse("something else")
        .getMatch();

    System.out.println("Matching Result: " + result);
  }
}
