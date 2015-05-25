package com.leacox.motif.pattern;

import static com.leacox.motif.ArgumentMatchers.eq;
import static com.leacox.motif.pattern.TuplePattern.caseTuple2;

import com.leacox.motif.ArgumentMatcher;
import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.function.Function2;
import com.leacox.motif.tuple.Tuple2;

/**
 * @author John Leacox
 */
public final class CaseClassPattern {
  private CaseClassPattern() {
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
