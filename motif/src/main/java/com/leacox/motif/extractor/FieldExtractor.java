package com.leacox.motif.extractor;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public interface FieldExtractor<T> {
  Optional<List<Object>> unapply(T value);

  Class<?> getExtractorClass();
}
