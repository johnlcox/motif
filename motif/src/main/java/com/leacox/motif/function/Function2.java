package com.leacox.motif.function;

/**
 * @author John Leacox
 */
@FunctionalInterface
public interface Function2<A, B, R> {
  /**
   * Applies this function to the given arguments.
   *
   * @param a the function argument one
   * @param b the function argument two
   * @return the function result
   */
  R apply(A a, B b);
}
