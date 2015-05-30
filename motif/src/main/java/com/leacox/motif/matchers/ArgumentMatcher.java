package com.leacox.motif.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * @author John Leacox
 */
public abstract class ArgumentMatcher<T> extends BaseMatcher<T> {
  @Override
  public abstract boolean matches(Object arg);

  @Override
  public void describeTo(Description description) {
    description.appendText(getClass().getSimpleName());
  }
}
