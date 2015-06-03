package com.leacox.motif.pattern;

import com.leacox.motif.function.Consumer0;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class OptionalPattern {
  private OptionalPattern() {
  }

  public static <T, R> Pattern<Optional<T>, R> caseSome(Function<T, R> function) {
    return Pattern.of(Optional::isPresent, o -> function.apply(o.get()));
  }

  public static <T> ConsumablePattern<Optional<T>> cazeSome(Consumer<T> consumer) {
    return ConsumablePattern.of(Optional::isPresent, o -> consumer.accept(o.get()));
  }

  public static <T, R> Pattern<Optional<T>, R> caseNone(Supplier<R> supplier) {
    return Pattern.of(o -> !o.isPresent(), o -> supplier.get());
  }

  public static <T> ConsumablePattern<Optional<T>> cazeNone(Consumer0 consumer) {
    return ConsumablePattern.of(o -> !o.isPresent(), o -> consumer.accept());
  }
}
