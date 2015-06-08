package com.leacox.motif.example;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.PrimitiveCases.caseLong;
import static com.leacox.motif.matchers.ArgumentMatchers.any;

/**
 * @author John Leacox
 */
public class Factorial {
  public long factConditional(long n) {
    if (n == 0) {
      return 1;
    } else {
      return n * factConditional(n - 1);
    }
  }

  public long factMatching(long n) {
    return match(n)
        .when(caseLong(0)).get(i -> 1l)
        .when(caseLong(any())).get(i -> i * factMatching(i - 1))
        .getMatch();
  }
}
