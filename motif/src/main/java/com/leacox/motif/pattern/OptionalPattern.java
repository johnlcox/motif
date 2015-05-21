package com.leacox.motif.pattern;

import com.leacox.motif.function.Consumer0;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public class OptionalPattern {
  public static <T, R> Pattern<Optional<T>, R> caseSome(Function<T, R> function) {
    return new BasePattern<>(Optional::isPresent, o -> function.apply(o.get()));
  }

  public static <T> ConsumablePattern<Optional<T>> caseSome(Consumer<T> consumer) {
    return new BaseConsumablePattern<>(Optional::isPresent, o -> consumer.accept(o.get()));
  }

  public static <T, R> Pattern<Optional<T>, R> caseNone(Supplier<R> supplier) {
    return new BasePattern<>(o -> !o.isPresent(), o -> supplier.get());
  }

  public static <T> ConsumablePattern<Optional<T>> caseNone(Consumer0 consumer) {
    return new BaseConsumablePattern<>(o -> !o.isPresent(), o -> consumer.accept());
  }
}
