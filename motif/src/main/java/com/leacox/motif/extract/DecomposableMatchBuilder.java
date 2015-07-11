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

import com.leacox.motif.extract.matchers.Matcher;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Abstract base class for all DecomposableMatchBuilder classes.
 *
 * @author John Leacox
 */
public abstract class DecomposableMatchBuilder<T> {
  final List<Matcher<Object>> fieldMatchers;
  final FieldExtractor<T> fieldExtractor;

  DecomposableMatchBuilder(List<Matcher<Object>> fieldMatchers, FieldExtractor<T> fieldExtractor) {
    this.fieldMatchers = fieldMatchers;
    this.fieldExtractor = fieldExtractor;
  }

  protected TreeMap<Integer, List<Matcher<Object>>> getChildMatchersByIndex(
      Map<Integer, DecomposableMatchBuilder> nestedBuildersByIndex) {
    TreeMap<Integer, List<Matcher<Object>>> childMatchersByIndex = new TreeMap<>();
    for (Map.Entry<Integer, DecomposableMatchBuilder> entry : nestedBuildersByIndex.entrySet()) {
      childMatchersByIndex.put(entry.getKey(), entry.getValue().fieldMatchers);
    }

    return childMatchersByIndex;
  }

  protected List<Matcher<Object>> getChildMatchers(
      Map<Integer, DecomposableMatchBuilder> nestedBuildersByIndex) {
    TreeMap<Integer, List<Matcher<Object>>> childMatchersByIndex =
        getChildMatchersByIndex(nestedBuildersByIndex);

    return NestedMatchers.buildNestedMatchers(this.fieldMatchers, childMatchersByIndex);
  }

  protected TreeMap<Integer, FieldExtractor> getChildExtractorsByIndex(
      Map<Integer, DecomposableMatchBuilder> nestedBuildersByIndex) {
    TreeMap<Integer, FieldExtractor> childExtractorsByIndex = new TreeMap<>();
    for (Map.Entry<Integer, DecomposableMatchBuilder> entry : nestedBuildersByIndex.entrySet()) {
      childExtractorsByIndex.put(entry.getKey(), entry.getValue().fieldExtractor);
    }

    return childExtractorsByIndex;
  }

  protected NestedFieldExtractor<T> getNestedFieldExtractor(
      Map<Integer, DecomposableMatchBuilder> buildersByIndex) {
    return new NestedFieldExtractor<>(fieldExtractor, getChildExtractorsByIndex(buildersByIndex));
  }
}
