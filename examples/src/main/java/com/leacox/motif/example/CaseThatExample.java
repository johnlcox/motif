package com.leacox.motif.example;

import static com.leacox.motif.fluent.FluentMotif.match;
import static com.leacox.motif.fluent.cases.CaseThatCases.caseEq;

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
