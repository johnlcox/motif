package com.leacox.motif.tuple;

/**
 * @author John Leacox
 */
public final class Tuple1<A> extends Tuple {
  private final A a;

  private Tuple1(A a) {
    super(a);

    this.a = a;
  }

  public static <A> Tuple1<A> of(A a) {
    return new Tuple1<>(a);
  }

  public A first() {
    return a;
  }
}
