package com.leacox.motif.matching;

import com.leacox.motif.MatchException;
import com.leacox.motif.pattern.Pattern;

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

  public <U extends T> OngoingMatchingR0<T, U, R> when(MatchingExtractor0<U> matchingExtractor) {
    return new OngoingMatchingR0<>(this, matchingExtractor.extractor);
  }

  public <U extends T, A> OngoingMatchingR1<T, U, A, R> when(
      MatchingExtractor1<U, A> matchingExtractor) {
    return new OngoingMatchingR1<>(this, matchingExtractor.extractor, matchingExtractor.toMatch);
  }

  public <U extends T, A, B> OngoingMatchingR2<T, U, A, B, R> when(
      MatchingExtractor2<U, A, B> matchingExtractor) {
    return new OngoingMatchingR2<>(
        this, matchingExtractor.extractor, matchingExtractor.toMatchA, matchingExtractor.toMatchB);
  }

  public <U extends T, A, B, C> OngoingMatchingR3<T, U, A, B, C, R> when(
      MatchingExtractor3<U, A, B, C> matchingExtractor) {
    return new OngoingMatchingR3<>(
        this, matchingExtractor.extractor, matchingExtractor.toMatchA, matchingExtractor.toMatchB,
        matchingExtractor.toMatchC);
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
