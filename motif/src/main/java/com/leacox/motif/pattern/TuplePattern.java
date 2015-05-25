package com.leacox.motif.pattern;

import com.leacox.motif.ArgumentMatcher;
import com.leacox.motif.function.Function2;
import com.leacox.motif.internal.ArgumentsComparator;
import com.leacox.motif.tuple.Tuple2;

/**
 * @author John Leacox
 */
public final class TuplePattern {
  private TuplePattern() {
  }

  public static <A, B, R> Pattern<Tuple2<A, B>, R> caseTuple2(
      ArgumentMatcher a, ArgumentMatcher b, Function2<A, B, R> function) {
    ArgumentMatcher[] matchers = new ArgumentMatcher[] {a, b};
    return Pattern.of(
        t -> ArgumentsComparator.argumentsMatch(matchers, t.toList()),
        t -> function.apply(t.first(), t.second()));
  }
}
