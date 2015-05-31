package com.leacox.motif.pattern;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.function.Function2;
import com.leacox.motif.function.Function3;
import com.leacox.motif.matchers.ArgumentMatcher;
import com.leacox.motif.tuple.Tuple1;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

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
}
