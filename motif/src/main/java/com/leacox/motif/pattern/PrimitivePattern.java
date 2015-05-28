package com.leacox.motif.pattern;

import java.util.function.IntFunction;

/**
 * @author John Leacox
 */
public final class PrimitivePattern {
  private PrimitivePattern() {
  }

  public static <R> IntPattern<R> caseInt(int i, IntFunction<R> function) {
    return IntPattern.of(j -> j == i, function::apply);
  }

  public static <R> Pattern<Integer, R> caseIntBoxing(int i, IntFunction<R> function) {
    return Pattern.of(j -> j == i, function::apply);
  }
}
