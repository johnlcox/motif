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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A field extractor for extracting a primitive value.
 *
 * @author John Leacox
 */
final class PrimitiveFieldExtractor<T> implements FieldExtractor<T> {
  private final PrimitiveExtractor<T> primitiveExtractor;

  PrimitiveFieldExtractor(Class<T> primitiveType) {
    this.primitiveExtractor = PrimitiveExtractor.create(primitiveType);
  }

  @Override
  public Optional<List<Object>> unapply(T t) {
    Optional<T> opt = primitiveExtractor.unapply(t);
    if (!opt.isPresent()) {
      return Optional.empty();
    }

    List<Object> fields = new ArrayList<>();
    fields.add(opt.get());

    return Optional.of(fields);
  }

  @Override
  public Class getExtractorClass() {
    return primitiveExtractor.getExtractorClass();
  }
}
