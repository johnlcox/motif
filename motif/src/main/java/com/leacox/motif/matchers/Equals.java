package com.leacox.motif.matchers;

import java.util.Objects;

/**
 * @author John Leacox
 */
class Equals extends ArgumentMatcher<Object> {
  private final Object expected;

  public Equals(Object expected) {
    this.expected = expected;
  }

  @Override
  public boolean matches(Object arg) {
    // TODO: Is special logic needed for arrays?
    return Objects.equals(expected, arg);
  }
}
