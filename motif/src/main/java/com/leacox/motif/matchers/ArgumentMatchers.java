package com.leacox.motif.matchers;

/**
 * @author John Leacox
 */
public class ArgumentMatchers {
  private ArgumentMatchers() {
  }

  @SuppressWarnings("unchecked")
  public static <T> Matcher<T> any() {
    return Any.ANY;
  }

  @SuppressWarnings("unchecked")
  public static <T> Matcher<T> eq(T value) {
    return (Matcher<T>) new Equals(value);
  }
}
