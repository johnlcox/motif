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

import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple3;

import java.util.List;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder3<T, A, B, C> extends DecomposableMatchBuilder<T> {
  final Tuple3<Integer, Integer, Integer> extractedIndexes;

  public DecomposableMatchBuilder3(
      List<Matcher<Object>> fieldMatchers, Tuple3<Integer, Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);

    this.extractedIndexes = extractedIndexes;
  }

  public DecomposableMatch3<T, A, B, C> build() {
    return new DecomposableMatch3<>(fieldMatchers, extractedIndexes, fieldExtractor);
  }
}
