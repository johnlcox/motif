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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public class OptionalNoneFieldExtractor <T> implements FieldExtractor<Optional<T>> {
  OptionalNoneExtractor<T> noneExtractor = new OptionalNoneExtractor<>();

  @Override
  public Optional<List<Object>> unapply(Optional<T> value) {
    if (!noneExtractor.unapply(value)) {
      return Optional.empty();
    }

    return Optional.of(Collections.emptyList());
  }

  @Override
  public Class<?> getExtractorClass() {
    return noneExtractor.getExtractorClass();
  }
}
