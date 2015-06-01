package com.leacox.motif.function;

/**
 * @author John Leacox
 */
@FunctionalInterface
public interface Consumer3<A, B, C> {
  /**
   * Performs this operation on the given arguments.
   *
   * @param a the input argument one
   * @param b the input argument two
   * @param c the input argument three
   */
  void accept(A a, B b, C c);
}
