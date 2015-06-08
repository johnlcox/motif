package com.leacox.motif.matching;

import com.leacox.motif.extractor.Extractor0;

/**
 * @author John Leacox
 */
public final class MatchingExtractor0<T> {
  final Extractor0<T> extractor;

  private MatchingExtractor0(Extractor0<T> extractor) {
    this.extractor = extractor;
  }

  public static <T> MatchingExtractor0<T> create(Extractor0<T> extractor) {
    return new MatchingExtractor0<>(extractor);
  }
}
