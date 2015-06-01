package com.leacox.motif.function;

/**
 * @author John Leacox
 */
@FunctionalInterface
public interface Function3<A, B, C, R> {
  /**
   * Applies this function to the given arguments.
   *
   * @param a the function argument one
   * @param b the function argument two
   * @param c the function argument three
   * @return the function result
   */
  R apply(A a, B b, C c);
}
