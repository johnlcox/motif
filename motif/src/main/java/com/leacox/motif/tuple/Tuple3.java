package com.leacox.motif.tuple;

/**
 * @author John Leacox
 */
public final class Tuple3<A, B, C> extends Tuple {
  private final A a;
  private final B b;
  private final C c;

  private Tuple3(A a, B b, C c) {
    super(a, b, c);

    this.a = a;
    this.b = b;
    this.c = c;
  }

  public static <A, B, C> Tuple3<A, B, C> of(A a, B b, C c) {
    return new Tuple3<>(a, b, c);
  }

  public A first() {
    return a;
  }

  public B second() {
    return b;
  }

  public C third() {
    return c;
  }
}
