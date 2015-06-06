package com.leacox.motif.fluent;

import com.leacox.motif.Extractor0;

/**
 * @author John Leacox
 */
public class MatchBuilder0<T, A> {
  final Extractor0<T> extractor;

  public MatchBuilder0(Extractor0<T> extractor) {
    this.extractor = extractor;
  }

  //public PatternBuilder0<T, A> with(FluentMatching<T> fluentMatching) {
  //  return new PatternBuilder0<>(fluentMatching, extractor);
  //}
}
