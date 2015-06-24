package com.leacox.motif.cases;

import com.leacox.motif.extraction.Extractor3;
import com.leacox.motif.tuple.Tuple3;

import java.util.Optional;

/**
 * @author John Leacox
 */
public class Tuple3Extractor<A, B, C> implements Extractor3<Tuple3<A, B, C>, A, B, C> {
  @Override
  public Optional<Tuple3<A, B, C>> unapply(Tuple3<A, B, C> tuple3) {
    return Optional.ofNullable(tuple3);
  }

  @Override
  public Class<Tuple3> getExtractorClass() {
    return Tuple3.class;
  }
}
