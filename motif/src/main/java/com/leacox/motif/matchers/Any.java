package com.leacox.motif.matchers;

/**
 * @author John Leacox
 */
public class Any<T> extends ArgumentMatcher {
  private final T t = null;
  public static final Any ANY = new Any();

  public Any() {
  }

  @Override
  public boolean matches(Object arg) {
    return true;
  }
}
