package com.leacox.motif.fluent;

import com.leacox.motif.MatchException;
import com.leacox.motif.function.Consumer0;
import com.leacox.motif.pattern.ConsumablePattern;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author John Leacox
 */
public class FluentMatchingC<T> {
  final T value;
  private final List<ConsumablePattern<T>> patterns = new ArrayList<>();

  FluentMatchingC(T value) {
    this.value = value;
  }

  public void addPattern(ConsumablePattern<T> pattern) {
    patterns.add(pattern);
  }

  public OngoingMatchingC0<T> when(MatchingExtractor0<T> matchBuilder) {
    return new OngoingMatchingC0<>(this, matchBuilder.extractor);
  }

  public <A> OngoingMatchingC1<T, A> when(MatchingExtractor1<T, A> matchBuilder) {
    return new OngoingMatchingC1<>(this, matchBuilder.extractor, matchBuilder.toMatch);
  }

  public <A, B> OngoingMatchingC2<T, A, B> when(MatchingExtractor2<T, A, B> matchBuilder) {
    return new OngoingMatchingC2<>(
        this, matchBuilder.extractor, matchBuilder.toMatchA, matchBuilder.toMatchB);
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
