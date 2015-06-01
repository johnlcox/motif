package com.leacox.motif.pattern;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import org.hamcrest.Matcher;

import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class CaseThatPatterns {
  private CaseThatPatterns() {
  }

  public static <R> Pattern<Object, R> caseThat(Matcher<Object> matcher, Supplier<R> supplier) {
    return Pattern.of(matcher::matches, o -> supplier.get());
  }

  public static <R> Pattern<Object, R> caseEq(Object o, Supplier<R> supplier) {
    return caseThat(eq(o), supplier);
  }
}
