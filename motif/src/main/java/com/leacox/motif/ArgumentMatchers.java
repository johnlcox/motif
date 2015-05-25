package com.leacox.motif;

import com.leacox.motif.internal.matchers.Any;
import com.leacox.motif.internal.matchers.Equals;

/**
 * @author John Leacox
 */
public class ArgumentMatchers {
  private ArgumentMatchers() {
  }

  public static ArgumentMatcher any() {
    return Any.ANY;
  }

  public static <T> ArgumentMatcher eq(T value) {
    return new Equals(value);
  }
}
