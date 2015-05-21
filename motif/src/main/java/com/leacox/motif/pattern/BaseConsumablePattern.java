package com.leacox.motif.pattern;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
public class BaseConsumablePattern<T> implements ConsumablePattern<T> {
  private final Function<T, Boolean> matcher;
  private final Consumer<T> consumer;

  public BaseConsumablePattern(Function<T, Boolean> matcher, Consumer<T> consumer) {
    this.consumer = consumer;
    this.matcher = matcher;
  }

  @Override
  public boolean matches(T value) {
    return matcher.apply(value);
  }

  public void consume(T value) {
    consumer.accept(value);
  }
}
