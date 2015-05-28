package com.leacox.motif.pattern;

import java.util.function.IntFunction;

/**
 * @author John Leacox
 */
public final class PrimitivePattern {
  private PrimitivePattern() {
  }

  public static <R> Pattern<Integer, R> caseInt(int i, IntFunction<R> function) {
    return Pattern.of(j -> j == i, function::apply);
  }
}
