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
 * An operation on three arguments that returns no result.
 *
 * @param <A> the type of argument one
 * @param <B> the type of argument two
 * @param <C> the type of argument three
 *
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
