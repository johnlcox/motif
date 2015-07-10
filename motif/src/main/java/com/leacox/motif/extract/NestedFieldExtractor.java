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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * @author John Leacox
 */
final class NestedFieldExtractor<T> implements FieldExtractor<T> {
  private final FieldExtractor<T> parent;
  private final Map<Integer, FieldExtractor> childExtractorsByIndex;

  NestedFieldExtractor(
      FieldExtractor<T> parent, TreeMap<Integer, FieldExtractor> childExtractorsByIndex) {
    this.parent = parent;
    this.childExtractorsByIndex = childExtractorsByIndex;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Optional<List<Object>> unapply(T value) {
    Optional<List<Object>> fieldsOpt = parent.unapply(value);

    if (!fieldsOpt.isPresent()) {
      return Optional.empty();
    }

    List<Object> fields = fieldsOpt.get();
    int currentIndex = 0;
    List<Object> newFields = new ArrayList<>();
    for (Map.Entry<Integer, FieldExtractor> entry : childExtractorsByIndex.entrySet()) {
      int childIndex = entry.getKey();
      FieldExtractor child = entry.getValue();
      if (!child.getExtractorClass().isAssignableFrom(fields.get(childIndex).getClass())) {
        return Optional.empty();
      }

      @SuppressWarnings("unchecked")
      Optional<List<Object>> childFieldsOpt = child.unapply(fields.get(childIndex));

      if (!childFieldsOpt.isPresent()) {
        return Optional.empty();
      }

      List<Object> childFields = childFieldsOpt.get();
      newFields.addAll(fields.subList(currentIndex, childIndex));
      newFields.addAll(childFields);

      currentIndex = childIndex + 1;
    }

    newFields.addAll(fields.subList(currentIndex, fields.size()));

    return Optional.of(newFields);
  }

  @Override
  public Class<?> getExtractorClass() {
    return parent.getExtractorClass();
  }
}
