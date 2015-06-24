package com.leacox.motif.cases;

import com.leacox.motif.extraction.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public class Tuple2FieldExtractor<A, B> implements FieldExtractor<Tuple2<A, B>> {
  Tuple2Extractor<A, B> tuple2Extractor = new Tuple2Extractor<>();

  @Override
  public Optional<List<Object>> unapply(Tuple2<A, B> value) {
    Optional<Tuple2<A, B>> tuple2Opt = tuple2Extractor.unapply(value);
    if (!tuple2Opt.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(value.toList());
  }

  @Override
  public Class<?> getExtractorClass() {
    return tuple2Extractor.getExtractorClass();
  }
}
