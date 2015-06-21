package com.leacox.motif.extraction;

import com.leacox.motif.tuple.Tuple2;

import java.util.Objects;
import java.util.Optional;

/**
 * @author John Leacox
 */
public interface Extractor1<T, A> {
  //T apply(A a);

  Optional<A> unapply(T t);

  Class getExtractorClass();

  default <B> Extractor1<T, B> decomposeFirst(Extractor1<A, B> after) {
    Objects.requireNonNull(after);

    return new Extractor1<T, B>() {
      @Override
      public Optional<B> unapply(T t) {
        Optional<A> result = Extractor1.this.unapply(t);
        if (result.isPresent()) {
          return after.unapply(result.get());
        } else {
          return Optional.empty();
        }
      }

      @Override
      public Class getExtractorClass() {
        return Extractor1.this.getExtractorClass();
      }
    };
  }

  default <B, C> Extractor2<T, B, C> decomposeFirst(Extractor2<A, B, C> after) {
    Objects.requireNonNull(after);

    return new Extractor2<T, B, C>() {
      @Override
      public Optional<Tuple2<B, C>> unapply(T t) {
        Optional<A> result = Extractor1.this.unapply(t);
        if (result.isPresent()) {
          return after.unapply(result.get());
        } else {
          return Optional.empty();
        }
      }

      @Override
      public Class getExtractorClass() {
        return Extractor1.this.getExtractorClass();
      }
    };
  }
}
