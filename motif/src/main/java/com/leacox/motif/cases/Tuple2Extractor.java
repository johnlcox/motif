package com.leacox.motif.cases;

import com.leacox.motif.extraction.Extractor2;
import com.leacox.motif.tuple.Tuple2;

import java.util.Optional;

/**
 * @author John Leacox
 */
public class Tuple2Extractor<A, B> implements Extractor2<Tuple2<A, B>, A, B> {
  @Override
  public Optional<Tuple2<A, B>> unapply(Tuple2<A, B> tuple2) {
    return Optional.ofNullable(tuple2);
  }

  @Override
  public Class<Tuple2> getExtractorClass() {
    return Tuple2.class;
  }
}
