package com.leacox.motif.matchers;

/**
 * @author John Leacox
 */
public class ArgumentMatchers {
  private ArgumentMatchers() {
  }

  public static <T> ArgumentMatcher<T> any() {
    return (ArgumentMatcher<T>) Any.ANY;
  }

  public static <T> ArgumentMatcher eq(T value) {
    return new Equals(value);
  }
}
