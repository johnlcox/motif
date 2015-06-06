package com.leacox.motif.fluent;

import com.leacox.motif.Extractor1;
import com.leacox.motif.pattern.Pattern;

import org.hamcrest.Matcher;

import java.util.function.Function;

/**
 * @author John Leacox
 */
public class PatternBuilderR1<T, S, R> {
  private final FluentMatchingR<T, S, R> fluentMatchingR;
  private final Extractor1<T, S> extractor;
  private final Matcher<S> toMatch;

  PatternBuilderR1(
      FluentMatchingR<T, S, R> fluentMatchingR, Extractor1<T, S> extractor, Matcher<S> toMatch) {
    this.fluentMatchingR = fluentMatchingR;
    this.extractor = extractor;
    this.toMatch = toMatch;
  }

  public FluentMatchingR<T, S, R> is(Function<S, R> function) {
    fluentMatchingR.addPattern(
        Pattern
            .<T, R>of(
                t -> extractor.unapply(t).isPresent() && toMatch
                    .matches(extractor.unapply(t).get()),
                t -> function.apply(
                    extractor.unapply(t).get())));

    return fluentMatchingR;
  }
}
