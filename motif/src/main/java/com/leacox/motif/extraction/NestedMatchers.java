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
