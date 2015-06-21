package com.leacox.motif.matchers;

/**
 * An interface for argument matchers when building motif pattern cases.
 *
 * @author John Leacox
 */
public interface Matcher<T> {
  boolean matches(Object arg);
}
