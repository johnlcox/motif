package com.leacox.motif.tuple;

/**
 * @author John Leacox
 */
public final class Tuple2<A, B> extends Tuple {
  private final A a;
  private final B b;

  private Tuple2(A a, B b) {
    super(a, b);

    this.a = a;
    this.b = b;
  }

  public static <A, B> Tuple2<A, B> of(A a, B b) {
    return new Tuple2<>(a, b);
  }

  public A first() {
    return a;
  }

  public B second() {
    return b;
  }
}
