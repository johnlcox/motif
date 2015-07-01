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
package com.leacox.motif.function;

/**
 * A function of three arguments.
 *
 * @param <A> the type of argument one
 * @param <B> the type of argument two
 * @param <C> the type of argument three
 * @param <R> the type of the result
 *
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
