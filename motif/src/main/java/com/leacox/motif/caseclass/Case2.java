package com.leacox.motif.caseclass;

import com.leacox.motif.tuple.Tuple2;

/**
 * @author John Leacox
 */
public interface Case2<A, B> {
  Tuple2<A, B> extract();
}
