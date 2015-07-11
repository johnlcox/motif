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

import com.leacox.motif.extract.FieldExtractor;
import com.leacox.motif.tuple.Tuple3;

import java.util.List;
import java.util.Optional;

/**
 * Field extractor for {@link Tuple3}.
 *
 * @author John Leacox
 */
final class Tuple3FieldExtractor<A, B, C> implements FieldExtractor<Tuple3<A, B, C>> {
  private final Tuple3Extractor<A, B, C> tuple3Extractor = Tuple3Extractor.create();

  @Override
  public Optional<List<Object>> unapply(Tuple3<A, B, C> value) {
    Optional<Tuple3<A, B, C>> tuple3Opt = tuple3Extractor.unapply(value);
    if (!tuple3Opt.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(value.toList());
  }

  @Override
  public Class<?> getExtractorClass() {
    return tuple3Extractor.getExtractorClass();
  }
}
