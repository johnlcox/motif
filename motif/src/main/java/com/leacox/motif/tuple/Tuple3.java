/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
