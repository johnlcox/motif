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

package com.leacox.motif.cases;

import com.leacox.motif.extract.Extractor1;

import java.util.Optional;

/**
 * An extractor for extracting an {@link Optional}.
 *
 * @author John Leacox
 */
public class OptionalExtractor<T> implements Extractor1<Optional<T>, T> {
  private OptionalExtractor() {
  }

  /**
   * Creates a new instance of {@link OptionalExtractor}.
   */
  public static <A> OptionalExtractor<A> create() {
    return new OptionalExtractor<>();
  }

  @Override
  public Optional<T> unapply(Optional<T> t) {
    if (t.isPresent()) {
      return t;
    } else {
      return Optional.empty();
    }
  }

  @Override
  public Class getExtractorClass() {
    return Optional.class;
  }
}
