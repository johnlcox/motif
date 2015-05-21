package com.leacox.motif.function;

/**
 * @author John Leacox
 */
@FunctionalInterface
public interface Consumer2<A, B> {
  /**
   * Performs this operation on the given arguments.
   *
   * @param a the input argument one
   * @param b the input argument two
   */
  void accept(A a, B b);
}
