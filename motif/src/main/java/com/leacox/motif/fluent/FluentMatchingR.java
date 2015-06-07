package com.leacox.motif.fluent;

import com.leacox.motif.MatchException;
import com.leacox.motif.pattern.Pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author John Leacox
 */
public class FluentMatchingR<T, R> {
  final T value;
  private final List<Pattern<T, R>> patterns = new ArrayList<>();

  FluentMatchingR(T value) {
    this.value = value;
  }

  public void addPattern(Pattern<T, R> pattern) {
    patterns.add(pattern);
  }

  public OngoingMatchingR0<T, R> when(MatchingExtractor0<T> matchBuilder) {
    return new OngoingMatchingR0<>(this, matchBuilder.extractor);
  }

  public <A> OngoingMatchingR1<T, A, R> when(MatchingExtractor1<T, A> matchBuilder) {
    return new OngoingMatchingR1<>(this, matchBuilder.extractor, matchBuilder.toMatch);
  }

  public <A, B> OngoingMatchingR2<T, A, B, R> when(MatchingExtractor2<T, A, B> matchBuilder) {
    return new OngoingMatchingR2<>(
        this, matchBuilder.extractor, matchBuilder.toMatchA, matchBuilder.toMatchB);
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
