package com.leacox.motif.pattern;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
public final class TypeOfPattern {
  private TypeOfPattern() {
  }

  @SuppressWarnings("unchecked")
  public static <C, T, R> Pattern<T, R> caseTypeOf(
      Class<C> expectedClass, Function<C, R> function) {
    return Pattern.of(
        v -> v != null && expectedClass.isAssignableFrom(v.getClass()),
        v -> function.apply((C) v));
  }

  @SuppressWarnings("unchecked")
  public static <C, T> ConsumablePattern<T> cazeTypeOf(
      Class<C> expectedClass, Consumer<C> consumer) {
    return ConsumablePattern.of(
        v -> v != null && expectedClass.isAssignableFrom(v.getClass()),
        v -> consumer.accept((C) v));
  }
}
