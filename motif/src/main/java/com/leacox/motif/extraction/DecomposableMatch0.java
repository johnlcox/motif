package com.leacox.motif.extraction;

import com.leacox.motif.extraction.Extractor0;
import com.leacox.motif.extraction.FieldExtractor;

import com.leacox.motif.matchers.Matcher;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
final class DecomposableMatch0<T> implements Extractor0<T> {
  final List<Matcher<Object>> fieldMatchers;
  final FieldExtractor<T> fieldExtractor;

  DecomposableMatch0(
      List<Matcher<Object>> fieldMatchers, FieldExtractor<T> fieldExtractor) {
    this.fieldMatchers = fieldMatchers;
    this.fieldExtractor = fieldExtractor;
  }

  @Override
  public boolean unapply(T t) {
    Optional<List<Object>> fieldsOpt = fieldExtractor.unapply(t);
    if (!fieldsOpt.isPresent()) {
      return false;
    }

    List<Object> fields = fieldsOpt.get();
    if (fieldMatchers.size() != fields.size()) {
      return false;
    }

    for (int i = 0; i < fieldMatchers.size(); i++) {
      if (!fieldMatchers.get(i).matches(fields.get(i))) {
        return false;
      }
    }

    return true;
  }

  @Override
  public Class getExtractorClass() {
    return fieldExtractor.getExtractorClass();
  }
}
