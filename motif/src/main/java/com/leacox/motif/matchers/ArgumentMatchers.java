package com.leacox.motif.matchers;

/**
 * @author John Leacox
 */
public class ArgumentMatchers {
  private ArgumentMatchers() {
  }

  public static <T> ArgumentMatcher<T> any() {
    return Any.ANY;
  }

  public static <T> ArgumentMatcher<T> eq(T value) {
    return (ArgumentMatcher<T>) new Equals(value);
  }
}
