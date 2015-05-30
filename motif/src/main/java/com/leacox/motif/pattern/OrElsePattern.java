package com.leacox.motif.pattern;

import com.leacox.motif.function.Consumer0;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class OrElsePattern {
  private OrElsePattern() {
  }

  public static <T, R> Pattern<T, R> orElse(Function<T, R> function) {
    return Pattern.of(t -> true, function::apply);
  }

  public static <T, R> Pattern<T, R> orElse(Supplier<R> supplier) {
    return Pattern.of(t -> true, t -> supplier.get());
  }

  public static <T, R> Pattern<T, R> orElse(R result) {
    return Pattern.of(t -> true, t -> result);
  }

  public static <T> ConsumablePattern<T> otherwise(Consumer<T> consumer) {
    return ConsumablePattern.of(t -> true, consumer::accept);
  }

  public static <T> ConsumablePattern<T> otherwise(Consumer0 consumer) {
    return new ConsumablePattern<T>() {

      @Override
      public boolean matches(T value) {
        return true;
      }

      @Override
      public void consume(T value) {
        consumer.accept();
      }
    };
  }
}
