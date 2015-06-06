package com.leacox.motif.fluent;

import com.leacox.motif.Extractor1;
import com.leacox.motif.pattern.ConsumablePattern;
import com.leacox.motif.pattern.Pattern;

import org.hamcrest.Matcher;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
public class PatternBuilder1<T, A> {
  private final Extractor1<T, A> extractor;
  private final T value;
  private final Matcher<A> toMatch;

  PatternBuilder1(Extractor1<T, A> extractor, T value, Matcher<A> toMatch) {
    this.extractor = extractor;
    this.value = value;
    this.toMatch = toMatch;
  }

  public <R> FluentMatchingR<T, A, R> is(Function<A, R> function) {
    FluentMatchingR<T, A, R> fluentMatchingR = new FluentMatchingR<>(value);

    fluentMatchingR.addPattern(
        Pattern.of(
            t -> extractor.unapply(t).isPresent() && toMatch.matches(extractor.unapply(t).get()),
            t -> function.apply(
                extractor.unapply(t).get())));

    return fluentMatchingR;
  }

  public FluentMatchingC<T, A> is(Consumer<A> consumer) {
    FluentMatchingC<T, A> fluentMatchingC = new FluentMatchingC<>(value);

    fluentMatchingC.addPattern(
        ConsumablePattern.of(
            t -> extractor.unapply(t).isPresent() && toMatch.matches(extractor.unapply(t).get()),
            t -> consumer.accept(extractor.unapply(t).get())));

    return fluentMatchingC;
  }
}
