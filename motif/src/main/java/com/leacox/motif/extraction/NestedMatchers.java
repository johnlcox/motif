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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author John Leacox
 */
final class NestedMatchers {
  private NestedMatchers() {
  }

  static List<Matcher<Object>> buildNestedMatchers(
      List<Matcher<Object>> original,
      TreeMap<Integer, List<Matcher<Object>>> childMatchersByIndex) {
    List<Matcher<Object>> matchers = new ArrayList<>();

    int currentIndex = 0;
    for (Map.Entry<Integer, List<Matcher<Object>>> entry : childMatchersByIndex.entrySet()) {
      int childIndex = entry.getKey();
      List<Matcher<Object>> childMatchers = entry.getValue();

      matchers.addAll(original.subList(currentIndex, childIndex));
      matchers.addAll(childMatchers);

      currentIndex = childIndex + 1;
    }

    matchers.addAll(original.subList(currentIndex, original.size()));

    return matchers;
  }
}
