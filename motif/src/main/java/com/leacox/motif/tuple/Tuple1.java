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
 * A tuple with a single value.
 *
 * @author John Leacox
 */
public final class Tuple1<A> extends Tuple {
  private final A a;

  private Tuple1(A a) {
    super(a);

    this.a = a;
  }

  /**
   * Creates a new instance of {@link Tuple1} with the specified value.
   */
  public static <A> Tuple1<A> of(A a) {
    return new Tuple1<>(a);
  }

  /**
   * Returns the first and only value.
   */
  public A first() {
    return a;
  }
}
