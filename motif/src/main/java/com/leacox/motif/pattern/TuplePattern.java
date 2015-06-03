package com.leacox.motif.pattern;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.function.Consumer2;
import com.leacox.motif.function.Consumer3;
import com.leacox.motif.function.Function2;
import com.leacox.motif.function.Function3;
import com.leacox.motif.matchers.ArgumentMatcher;
import com.leacox.motif.tuple.Tuple1;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
public final class TuplePattern {
  private TuplePattern() {
  }

  public static <A, R> Pattern<Tuple1<A>, R> caseTuple1(A a, Function<A, R> function) {
    return caseTuple1(eq(a), function);
  }

  public static <A, R> Pattern<Tuple1<A>, R> caseTuple1(
      ArgumentMatcher a, Function<A, R> function) {
    ArgumentMatcher[] matchers = new ArgumentMatcher[] {a};
    return Pattern.of(
        t -> ArgumentsComparator.argumentsMatch(matchers, t.toList()),
        t -> function.apply(t.first()));
  }

  public static <A> ConsumablePattern<Tuple1<A>> cazeTuple1(A a, Consumer<A> consumer) {
    return cazeTuple1(eq(a), consumer);
  }

  public static <A> ConsumablePattern<Tuple1<A>> cazeTuple1(
      ArgumentMatcher a, Consumer<A> consumer) {
    ArgumentMatcher[] matchers = new ArgumentMatcher[] {a};
    return ConsumablePattern.of(
        t -> ArgumentsComparator.argumentsMatch(matchers, t.toList()),
        t -> consumer.accept(t.first()));
  }

  public static <A, B, R> Pattern<Tuple2<A, B>, R> caseTuple2(
      A a, B b, Function2<A, B, R> function) {
    return caseTuple2(eq(a), eq(b), function);
  }

  public static <A, B, R> Pattern<Tuple2<A, B>, R> caseTuple2(
      ArgumentMatcher a, ArgumentMatcher b, Function2<A, B, R> function) {
    ArgumentMatcher[] matchers = new ArgumentMatcher[] {a, b};
    return Pattern.of(
        t -> ArgumentsComparator.argumentsMatch(matchers, t.toList()),
        t -> function.apply(t.first(), t.second()));
  }

  public static <A, B> ConsumablePattern<Tuple2<A, B>> cazeTuple2(
      A a, B b, Consumer2<A, B> consumer) {
    return cazeTuple2(eq(a), eq(b), consumer);
  }

  public static <A, B> ConsumablePattern<Tuple2<A, B>> cazeTuple2(
      ArgumentMatcher a, ArgumentMatcher b, Consumer2<A, B> consumer) {
    ArgumentMatcher[] matchers = new ArgumentMatcher[] {a, b};
    return ConsumablePattern.of(
        t -> ArgumentsComparator.argumentsMatch(matchers, t.toList()),
        t -> consumer.accept(t.first(), t.second()));
  }

  public static <A, B, C, R> Pattern<Tuple3<A, B, C>, R> caseTuple3(
      A a, B b, C c, Function3<A, B, C, R> function) {
    return caseTuple3(eq(a), eq(b), eq(c), function);
  }

  public static <A, B, C, R> Pattern<Tuple3<A, B, C>, R> caseTuple3(
      ArgumentMatcher a, ArgumentMatcher b, ArgumentMatcher c, Function3<A, B, C, R> function) {
    ArgumentMatcher[] matchers = new ArgumentMatcher[] {a, b, c};
    return Pattern.of(
        t -> ArgumentsComparator.argumentsMatch(matchers, t.toList()),
        t -> function.apply(t.first(), t.second(), t.third()));
  }

  public static <A, B, C> ConsumablePattern<Tuple3<A, B, C>> cazeTuple3(
      A a, B b, C c, Consumer3<A, B, C> consumer) {
    return cazeTuple3(eq(a), eq(b), eq(c), consumer);
  }

  public static <A, B, C> ConsumablePattern<Tuple3<A, B, C>> cazeTuple3(
      ArgumentMatcher a, ArgumentMatcher b, ArgumentMatcher c, Consumer3<A, B, C> consumer) {
    ArgumentMatcher[] matchers = new ArgumentMatcher[] {a, b, c};
    return ConsumablePattern.of(
        t -> ArgumentsComparator.argumentsMatch(matchers, t.toList()),
        t -> consumer.accept(t.first(), t.second(), t.third()));
  }
}
