package com.leacox.motif.pattern;

import static com.leacox.motif.ArgumentMatchers.eq;
import static com.leacox.motif.pattern.TuplePattern.caseTuple1;
import static com.leacox.motif.pattern.TuplePattern.caseTuple2;

import com.leacox.motif.ArgumentMatcher;
import com.leacox.motif.caseclass.Case1;
import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.function.Function2;
import com.leacox.motif.tuple.Tuple1;
import com.leacox.motif.tuple.Tuple2;

import java.util.function.Function;

/**
 * @author John Leacox
 */
public final class CaseClassPattern {
  private CaseClassPattern() {
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
}
