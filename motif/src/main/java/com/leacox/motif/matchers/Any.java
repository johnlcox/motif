package com.leacox.motif.matchers;

/**
 * @author John Leacox
 */
class Any extends ArgumentMatcher {
  public static final Any ANY = new Any();

  private Any() {
  }

  @Override
  public boolean matches(Object arg) {
    return true;
  }
}
