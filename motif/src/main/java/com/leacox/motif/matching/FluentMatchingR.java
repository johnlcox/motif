package com.leacox.motif.matching;

import com.leacox.motif.MatchException;
import com.leacox.motif.extraction.DecomposableMatchBuilder0;
import com.leacox.motif.extraction.DecomposableMatchBuilder1;
import com.leacox.motif.extraction.DecomposableMatchBuilder2;
import com.leacox.motif.extraction.DecomposableMatchBuilder3;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public final class FluentMatchingR<T, R> {
  final T value;
  private final List<Pattern<T, R>> patterns = new ArrayList<>();

  FluentMatchingR(T value) {
    this.value = value;
  }

  public void addPattern(Pattern<T, R> pattern) {
    patterns.add(pattern);
  }

  public <U extends T> OngoingMatchingR0<T, U, R> when(
      DecomposableMatchBuilder0<U> decomposableMatchBuilder) {
    return new OngoingMatchingR0<>(this, decomposableMatchBuilder.build());
  }

  public <U extends T, A> OngoingMatchingR1<T, U, A, R> when(
      DecomposableMatchBuilder1<U, A> decomposableMatchBuilder) {
    return new OngoingMatchingR1<>(this, decomposableMatchBuilder.build());
  }

  public <U extends T, A, B> OngoingMatchingR2<T, U, A, B, R> when(
      DecomposableMatchBuilder2<U, A, B> decomposableMatchBuilder) {
    return new OngoingMatchingR2<>(this, decomposableMatchBuilder.build());
  }

  public <U extends T, A, B, C> OngoingMatchingR3<T, U, A, B, C, R> when(
      DecomposableMatchBuilder3<U, A, B, C> decomposableMatchBuilder) {
    return new OngoingMatchingR3<>(this, decomposableMatchBuilder.build());
  }

  public R getMatch() {
    for (Pattern<T, R> pattern : patterns) {
      if (pattern.matches(value)) {
        return pattern.apply(value);
      }
    }

    throw new MatchException("No match found for " + value);
  }

  // TODO: Should these execute the match immediately but skip the MatchException?
  public FluentMatchingR<T, R> orElse(Function<T, R> function) {
    patterns.add(Pattern.of(t -> true, function::apply));
    return this;
  }

  public FluentMatchingR<T, R> orElse(Supplier<R> supplier) {
    patterns.add(Pattern.of(t -> true, t -> supplier.get()));
    return this;
  }

  public FluentMatchingR<T, R> orElse(R result) {
    patterns.add(Pattern.of(t -> true, t -> result));
    return this;
  }
}
