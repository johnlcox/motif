package com.leacox.motif.cases;

import com.leacox.motif.extraction.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public class Tuple3FieldExtractor<A, B, C> implements FieldExtractor<Tuple3<A, B, C>> {
  Tuple3Extractor<A, B, C> tuple3Extractor = new Tuple3Extractor<>();

  @Override
  public Optional<List<Object>> unapply(Tuple3<A, B, C> value) {
    Optional<Tuple3<A, B, C>> tuple3Opt = tuple3Extractor.unapply(value);
    if (!tuple3Opt.isPresent()) {
      return Optional.empty();
    }

    return Optional.of(value.toList());
  }

  @Override
  public Class<?> getExtractorClass() {
    return tuple3Extractor.getExtractorClass();
  }
}
