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
package com.leacox.motif.extraction;

import com.leacox.motif.extraction.Extractor1;
import com.leacox.motif.extraction.FieldExtractor;

import com.leacox.motif.matchers.Matcher;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
final class DecomposableMatch1<T, A> implements Extractor1<T, A> {
  final List<Matcher<Object>> fieldMatchers;
  final int extractedIndex;
  final FieldExtractor<T> fieldExtractor;

  DecomposableMatch1(
      List<Matcher<Object>> fieldMatchers, int extractedIndex, FieldExtractor<T> fieldExtractor) {
    this.fieldMatchers = fieldMatchers;
    this.extractedIndex = extractedIndex;
    this.fieldExtractor = fieldExtractor;
  }

  @Override
  public Optional<A> unapply(T t) {
    Optional<List<Object>> fieldsOpt = fieldExtractor.unapply(t);
    if (!fieldsOpt.isPresent()) {
      return Optional.empty();
    }

    List<Object> fields = fieldsOpt.get();
    if (fieldMatchers.size() != fields.size()) {
      return Optional.empty();
    }

    for (int i = 0; i < fieldMatchers.size(); i++) {
      if (!fieldMatchers.get(i).matches(fields.get(i))) {
        return Optional.empty();
      }
    }

    return Optional.of(getMatchedField(fields));
  }

  @Override
  public Class getExtractorClass() {
    return fieldExtractor.getExtractorClass();
  }

  private A getMatchedField(List<Object> fields) {
    A first = (A) fields.get(extractedIndex);
    return first;
  }
}
