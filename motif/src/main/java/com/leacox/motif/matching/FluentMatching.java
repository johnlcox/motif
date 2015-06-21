package com.leacox.motif.matching;

import com.leacox.motif.extraction.DecomposableMatchBuilder0;
import com.leacox.motif.extraction.DecomposableMatchBuilder1;
import com.leacox.motif.extraction.DecomposableMatchBuilder2;
import com.leacox.motif.extraction.DecomposableMatchBuilder3;

/**
 * @author John Leacox
 */
public final class FluentMatching<T> {
  final T value;

  public FluentMatching(T value) {
    this.value = value;
  }

  public <U extends T> InitialMatching0<T, U> when(
      DecomposableMatchBuilder0<U> decomposableMatchBuilder) {
    return new InitialMatching0<>(decomposableMatchBuilder.build(), value);
  }

  public <U extends T, A> InitialMatching1<T, U, A> when(
      DecomposableMatchBuilder1<U, A> decomposableMatchBuilder) {
    return new InitialMatching1<>(decomposableMatchBuilder.build(), value);
  }

  public <U extends T, A, B> InitialMatching2<T, U, A, B> when(
      DecomposableMatchBuilder2<U, A, B> decomposableMatchBuilder) {
    return new InitialMatching2<>(decomposableMatchBuilder.build(), value);
  }

  public <U extends T, A, B, C> InitialMatching3<T, U, A, B, C> when(
      DecomposableMatchBuilder3<U, A, B, C> decomposableMatchBuilder) {
    return new InitialMatching3<>(decomposableMatchBuilder.build(), value);
  }
}
