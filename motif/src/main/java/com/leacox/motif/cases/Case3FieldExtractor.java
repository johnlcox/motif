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

import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.extract.FieldExtractor;
import com.leacox.motif.tuple.Tuple3;

import java.util.List;
import java.util.Optional;

/**
 * Field extractor for {@link Case3}.
 *
 * @author John Leacox
 */
final class Case3FieldExtractor<T extends Case3<A, B, C>, A, B, C>
    implements FieldExtractor<T> {
  private final Case3Extractor<T, A, B, C> case3Extractor;

  Case3FieldExtractor(Class<T> caseClazz) {
    this.case3Extractor = new Case3Extractor<>(caseClazz);
  }

  @Override
  public Optional<List<Object>> unapply(T value) {
    Optional<Tuple3<A, B, C>> opt = case3Extractor.unapply(value);
    if (!opt.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(opt.get().toList());
  }

  @Override
  public Class<?> getExtractorClass() {
    return case3Extractor.getExtractorClass();
  }
}
