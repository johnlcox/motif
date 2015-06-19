package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.Extractor3;
import com.leacox.motif.extractor.FieldExtractor;
import com.leacox.motif.tuple.Tuple3;

import org.hamcrest.Matcher;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
final class DecomposableMatch3<T, A, B, C> implements Extractor3<T, A, B, C> {
  private final List<Matcher<Object>> fieldMatchers;
  private final Tuple3<Integer, Integer, Integer> extractedIndexes;
  private final FieldExtractor<T> fieldExtractor;

  DecomposableMatch3(
      List<Matcher<Object>> fieldMatchers, Tuple3<Integer, Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    this.fieldMatchers = fieldMatchers;
    this.extractedIndexes = extractedIndexes;
    this.fieldExtractor = fieldExtractor;
  }

  @Override
  public Optional<Tuple3<A, B, C>> unapply(T t) {
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

    return Optional.of(getMatchedFields(fields));
  }

  @Override
  public Class getExtractorClass() {
    return fieldExtractor.getExtractorClass();
  }

  private Tuple3<A, B, C> getMatchedFields(List<Object> fields) {
    A first = (A) fields.get(extractedIndexes.first());
    B second = (B) fields.get(extractedIndexes.second());
    C third = (C) fields.get(extractedIndexes.third());
    return Tuple3.of(first, second, third);
  }
}
