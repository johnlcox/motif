package com.leacox.motif.example;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.pattern.PrimitivePattern.caseLong;

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
    return match(n).on(
        caseLong(0, i -> 1l),
        caseLong(any(), i -> i * factMatching(i - 1))
    );
  }
}
