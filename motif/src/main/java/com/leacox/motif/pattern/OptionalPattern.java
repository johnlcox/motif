package com.leacox.motif.pattern;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.function.Consumer0;
import com.leacox.motif.matchers.ArgumentMatcher;

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

  public static <T, R> Pattern<Optional<T>, R> caseSome(T t, Function<T, R> function) {
    return caseSome(eq(t), function);
  }

  public static <T, R> Pattern<Optional<T>, R> caseSome(
      ArgumentMatcher<T> a, Function<T, R> function) {
    return Pattern.of(t -> t.isPresent() && a.matches(t.get()), t -> function.apply(t.get()));
  }

  public static <T> ConsumablePattern<Optional<T>> cazeSome(Consumer<T> consumer) {
    return ConsumablePattern.of(Optional::isPresent, o -> consumer.accept(o.get()));
  }

  public static <T> ConsumablePattern<Optional<T>> cazeSome(T t, Consumer<T> consumer) {
    return cazeSome(eq(t), consumer);
  }

  public static <T> ConsumablePattern<Optional<T>> cazeSome(
      ArgumentMatcher<T> a, Consumer<T> consumer) {
    return ConsumablePattern
        .of(t -> t.isPresent() && a.matches(t.get()), t -> consumer.accept(t.get()));
  }

  public static <T, R> Pattern<Optional<T>, R> caseNone(Supplier<R> supplier) {
    return Pattern.of(o -> !o.isPresent(), o -> supplier.get());
  }

  public static <T> ConsumablePattern<Optional<T>> cazeNone(Consumer0 consumer) {
    return ConsumablePattern.of(o -> !o.isPresent(), o -> consumer.accept());
  }
}
