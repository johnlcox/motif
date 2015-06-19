package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.FieldExtractor;

import org.hamcrest.Matcher;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author John Leacox
 */
abstract class DecomposableMatchBuilder<T> {
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
}
