package com.leacox.motif.decomposition;

import com.leacox.motif.extractor.Extractor2;
import com.leacox.motif.extractor.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;

import com.leacox.motif.matchers.Matcher;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
final class DecomposableMatch2<T, A, B> implements Extractor2<T, A, B> {
  private final List<Matcher<Object>> fieldMatchers;
  private final Tuple2<Integer, Integer> extractedIndexes;
  private final FieldExtractor<T> fieldExtractor;

  DecomposableMatch2(
      List<Matcher<Object>> fieldMatchers, Tuple2<Integer, Integer> extractedIndexes,
      FieldExtractor<T> fieldExtractor) {
    this.fieldMatchers = fieldMatchers;
    this.extractedIndexes = extractedIndexes;
    this.fieldExtractor = fieldExtractor;
  }

  @Override
  public Optional<Tuple2<A, B>> unapply(T t) {
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

  private Tuple2<A, B> getMatchedFields(List<Object> fields) {
    A first = (A) fields.get(extractedIndexes.first());
    B second = (B) fields.get(extractedIndexes.second());
    return Tuple2.of(first, second);
  }
}
