package com.leacox.motif.pattern;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
public class TypeOfPattern {
  @SuppressWarnings("unchecked")
  public static <C, T, R> Pattern<T, R> caseTypeOf(
      Class<C> expectedClass, Function<C, R> function) {
    return new BasePattern<>(
        v -> v != null && expectedClass.isAssignableFrom(v.getClass()),
        v -> function.apply((C) v));
  }

  @SuppressWarnings("unchecked")
  public static <C, T> ConsumablePattern<T> caseTypeOf(
      Class<C> expectedClass, Consumer<C> consumer) {
    return new BaseConsumablePattern<>(
        v -> v != null && expectedClass.isAssignableFrom(v.getClass()),
        v -> consumer.accept((C) v));
  }
}
