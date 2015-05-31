package com.leacox.motif.caseclass;

import com.leacox.motif.tuple.Tuple3;

/**
 * @author John Leacox
 */
public interface Case3<A, B, C> {
  Tuple3<A, B, C> extract();
}
