package com.leacox.motif.internal.matchers;

import com.leacox.motif.ArgumentMatcher;

/**
 * @author John Leacox
 */
public class Any extends ArgumentMatcher {
  public static final Any ANY = new Any();

  private Any() {
  }

  @Override
  public boolean matches(Object arg) {
    return true;
  }
}
