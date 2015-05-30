package com.leacox.motif.pattern;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.pattern.PrimitivePattern.caseInt;

/**
 * @author John Leacox
 */
public class Factorial {
  public static int fact(int n) {
    if (n == 0) {
      return 1;
    } else {
      return n * fact(n - 1);
    }
  }

  public static int fact2(int n) {
    return match(n).on(
        caseInt(0, i -> 1),
        caseInt(any(), i -> i * fact2(i - 1))
    );
  }
}
