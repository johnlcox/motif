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

import com.leacox.motif.caseclass.Case1;
import com.leacox.motif.extract.FieldExtractor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
final class Case1FieldExtractor<T extends Case1<A>, A>
    implements FieldExtractor<T> {
  private final Case1Extractor<T, A> case1Extractor;

  Case1FieldExtractor(Class<T> caseClazz) {
    this.case1Extractor = new Case1Extractor<>(caseClazz);
  }

  @Override
  public Optional<List<Object>> unapply(T value) {
    Optional<A> opt = case1Extractor.unapply(value);
    if (!opt.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(Collections.singletonList(opt.get()));
  }

  @Override
  public Class<?> getExtractorClass() {
    return case1Extractor.getExtractorClass();
  }
}
