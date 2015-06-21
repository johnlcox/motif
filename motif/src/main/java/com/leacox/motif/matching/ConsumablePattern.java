package com.leacox.motif.matching;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
interface ConsumablePattern<T> {
  boolean matches(T value);

  void consume(T value);

  static <T> ConsumablePattern<T> of(Function<T, Boolean> matcher, Consumer<T> consumer) {
    return new ConsumablePattern<T>() {
      @Override
      public boolean matches(T value) {
        return matcher.apply(value);
      }

      @Override
      public void consume(T value) {
        consumer.accept(value);
      }
    };
  }
}
