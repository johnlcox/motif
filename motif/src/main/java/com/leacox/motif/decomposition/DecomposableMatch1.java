package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.extractor.FieldExtractor;

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
