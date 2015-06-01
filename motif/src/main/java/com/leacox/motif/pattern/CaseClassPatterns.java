package com.leacox.motif.pattern;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;
import static com.leacox.motif.pattern.TuplePattern.caseTuple1;
import static com.leacox.motif.pattern.TuplePattern.caseTuple2;
import static com.leacox.motif.pattern.TuplePattern.caseTuple3;
import static com.leacox.motif.pattern.TypeOfPattern.caseTypeOf;

import com.leacox.motif.caseclass.Case1;
import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.caseclass.Case3;
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
public final class CaseClassPatterns {
  private CaseClassPatterns() {
  }

  public static <T extends Case1<A>, A, R> Pattern<T, R> case1(A a, Function<A, R> function) {
    return case1(eq(a), function);
  }

  public static <T extends Case1<A>, A, R> Pattern<T, R> case1(
      ArgumentMatcher a, Function<A, R> function) {
    Pattern<Tuple1<A>, R> tuple1Pattern = caseTuple1(a, function);
    return Pattern
        .of(t -> tuple1Pattern.matches(t.extract()), t -> tuple1Pattern.apply(t.extract()));
  }

  public static <T extends Case2<A, B>, A, B, R> Pattern<T, R> case2(
      A a, B b, Function2<A, B, R> function) {
    return case2(eq(a), eq(b), function);
  }

  public static <T extends Case2<A, B>, A, B, R> Pattern<T, R> case2(
      ArgumentMatcher a, ArgumentMatcher b, Function2<A, B, R> function) {
    Pattern<Tuple2<A, B>, R> tuple2Pattern = caseTuple2(a, b, function);
    return Pattern
        .of(t -> tuple2Pattern.matches(t.extract()), t -> tuple2Pattern.apply(t.extract()));
  }

  public static <S extends Case3<A, B, C>, T, A, B, C, R> Pattern<T, R> case3(
      Class<S> clazz, A a, B b, C c, Function3<A, B, C, R> function) {
    return case3(clazz, eq(a), eq(b), eq(c), function);
  }

  public static <S extends Case3<A, B, C>, T, A, B, C, R> Pattern<T, R> case3(
      Class<S> clazz, ArgumentMatcher a, ArgumentMatcher b, ArgumentMatcher c,
      Function3<A, B, C, R> function) {
    Pattern<Tuple3<A, B, C>, R> tuple3Pattern = caseTuple3(a, b, c, function);
    Pattern<T, S> typeOfPattern = caseTypeOf(clazz, d -> d);
    return Pattern
        .of(
            t -> typeOfPattern.matches(t) && tuple3Pattern
                .matches(typeOfPattern.apply(t).extract()),
            t -> tuple3Pattern.apply(typeOfPattern.apply(t).extract()));
  }

  //public static <S extends Case3<A, B, C>, T, A, B, C> ConsumablePattern<T> case3(
  //    Class<S> clazz, A a, B b, C c, Consumer3<A, B, C> consumer) {
  //  return case3(clazz, eq(a), eq(b), eq(c), consumer);
  //}
  //
  //public static <S extends Case3<A, B, C>, T, A, B, C> ConsumablePattern<T> case3(
  //    Class<S> clazz, ArgumentMatcher a, ArgumentMatcher b, ArgumentMatcher c,
  //    Consumer3<A, B, C> consumer) {
  //  ConsumablePattern<Tuple3<A, B, C>> tuple3Pattern = caseTuple3(a, b, c, consumer);
  //  Pattern<T, S> typeOfPattern = caseTypeOf(clazz, d -> d);
  //  return ConsumablePattern
  //      .of(
  //          t -> typeOfPattern.matches(t) && tuple3Pattern
  //              .matches(typeOfPattern.apply(t).extract()),
  //          t -> tuple3Pattern.consume(typeOfPattern.apply(t).extract()));
  //}
}
