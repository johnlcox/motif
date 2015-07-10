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

package com.leacox.motif.extract;

import com.leacox.motif.tuple.Tuple3;

import java.util.Optional;

/**
 * An extractor for types with 3 fields.
 *
 * @param <T> the type to be extracted
 * @param <A> the type of the first extracted field
 * @param <B> the type of the second extracted field
 * @param <C> the type of the third extracted field
 *
 * @author John Leacox
 */
public interface Extractor3<T, A, B, C> {
  /**
   * Extracts the fields of {@code t} into an {@link Optional} {@link Tuple3} if it matches this
   * extractor.
   *
   * <p>If {@code t} cannot be extracted then an {@link Optional#empty()} is returned.
   *
   * @param t the value to extract fields from
   * @return an {@link Optional} of the extracted fields, or {@link Optional#empty()} if the fields
   *     cannot be extracted
   */
  Optional<Tuple3<A, B, C>> unapply(T t);

  /**
   * Returns the {@link Class} type that this extractor can extract.
   */
  Class<?> getExtractorClass();
}
