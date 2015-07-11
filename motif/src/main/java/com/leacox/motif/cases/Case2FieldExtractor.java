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

import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.extract.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;

import java.util.List;
import java.util.Optional;

/**
 * Field extractor for {@link Case2}.
 *
 * @author John Leacox
 */
final class Case2FieldExtractor<T extends Case2<A, B>, A, B>
    implements FieldExtractor<T> {
  private final Case2Extractor<T, A, B> case2Extractor;

  Case2FieldExtractor(Class<T> caseClazz) {
    this.case2Extractor = Case2Extractor.create(caseClazz);
  }

  @Override
  public Optional<List<Object>> unapply(T value) {
    Optional<Tuple2<A, B>> opt = case2Extractor.unapply(value);
    if (!opt.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(opt.get().toList());
  }

  @Override
  public Class<?> getExtractorClass() {
    return case2Extractor.getExtractorClass();
  }
}
