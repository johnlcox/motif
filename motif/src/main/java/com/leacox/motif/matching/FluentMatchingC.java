package com.leacox.motif.matching;

import com.leacox.motif.MatchException;
import com.leacox.motif.function.Consumer0;
import com.leacox.motif.pattern.ConsumablePattern;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author John Leacox
 */
public final class FluentMatchingC<T> {
  final T value;
  private final List<ConsumablePattern<T>> patterns = new ArrayList<>();

  FluentMatchingC(T value) {
    this.value = value;
  }

  public void addPattern(ConsumablePattern<T> pattern) {
    patterns.add(pattern);
  }

  public <U extends T> OngoingMatchingC0<T, U> when(MatchingExtractor0<U> matchingExtractor) {
    return new OngoingMatchingC0<>(this, matchingExtractor.extractor);
  }

  public <U extends T, A> OngoingMatchingC1<T, U, A> when(
      MatchingExtractor1<U, A> matchingExtractor) {
    return new OngoingMatchingC1<>(this, matchingExtractor.extractor, matchingExtractor.toMatch);
  }

  public <U extends T, A, B> OngoingMatchingC2<T, U, A, B> when(
      MatchingExtractor2<U, A, B> matchingExtractor) {
    return new OngoingMatchingC2<>(
        this, matchingExtractor.extractor, matchingExtractor.toMatchA, matchingExtractor.toMatchB);
  }

  public <U extends T, A, B, C> OngoingMatchingC3<T, U, A, B, C> when(
      MatchingExtractor3<U, A, B, C> matchingExtractor) {
    return new OngoingMatchingC3<>(
        this, matchingExtractor.extractor, matchingExtractor.toMatchA, matchingExtractor.toMatchB,
        matchingExtractor.toMatchC);
  }

  public void doMatch() {
    boolean matchFound = false;
    for (ConsumablePattern<T> pattern : patterns) {
      if (pattern.matches(value)) {
        pattern.consume(value);
        matchFound = true;
        break;
      }
    }

    if (!matchFound) {
      throw new MatchException("No match found for " + value);
    }
  }

  // TODO: Should these execute the match immediately but skip the MatchException?
  public FluentMatchingC<T> orElse(Consumer<T> consumer) {
    patterns.add(ConsumablePattern.of(t -> true, consumer::accept));
    return this;
  }

  public FluentMatchingC<T> orElse(Consumer0 consumer) {
    patterns.add(ConsumablePattern.of(t -> true, t -> consumer.accept()));
    return this;
  }
}
