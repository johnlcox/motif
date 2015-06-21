package com.leacox.motif.matchers;

/**
 * @author John Leacox
 */
public class Any<T> implements Matcher<T> {
  public static final Any ANY = new Any();

  private Any() {
  }

  @Override
  public boolean matches(Object arg) {
    return true;
  }
}
