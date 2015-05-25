package com.leacox.motif.pattern;

import com.leacox.motif.function.Consumer0;
import com.leacox.motif.function.Consumer2;
import com.leacox.motif.function.Function2;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class ListPattern {
  private ListPattern() {
  }

  public static <T, R> Pattern<List<T>, R> caseNil(Supplier<R> supplier) {
    return Pattern.of(List::isEmpty, l -> supplier.get());
  }

  public static <T> ConsumablePattern<List<T>> caseNil(Consumer0 consumer) {
    return ConsumablePattern.of(List::isEmpty, l -> consumer.accept());
  }

  public static <T, R> Pattern<List<T>, R> caseHeadNil(Function<T, R> function) {
    return Pattern.of(l -> l.size() == 1, l -> function.apply(l.get(0)));
  }

  public static <T> ConsumablePattern<List<T>> caseHeadNil(Consumer<T> consumer) {
    return ConsumablePattern.of(l -> l.size() == 1, l -> consumer.accept(l.get(0)));
  }

  public static <T, R> Pattern<List<T>, R> caseHeadTail(Function2<T, List<T>, R> function) {
    return Pattern.of(l -> l.size() > 1, l -> function.apply(l.get(0), l.subList(1, l.size())));
  }

  public static <T> ConsumablePattern<List<T>> caseHeadTail(Consumer2<T, List<T>> consumer) {
    return ConsumablePattern
        .of(l -> l.size() > 1, l -> consumer.accept(l.get(0), l.subList(1, l.size())));
  }
}
