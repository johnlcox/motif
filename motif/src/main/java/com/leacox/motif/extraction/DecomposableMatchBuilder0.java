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

import java.util.List;

/**
 * @author John Leacox
 */
public final class DecomposableMatchBuilder0<T> extends DecomposableMatchBuilder<T> {
  public DecomposableMatchBuilder0(
      List<Matcher<Object>> fieldMatchers, FieldExtractor<T> fieldExtractor) {
    super(fieldMatchers, fieldExtractor);
  }

  public DecomposableMatch0<T> build() {
    return new DecomposableMatch0<>(fieldMatchers, fieldExtractor);
  }
}
