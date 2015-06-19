package com.leacox.motif.extractor;

import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import java.util.Objects;
import java.util.Optional;

/**
 * @author John Leacox
 */
public interface Extractor2<T, A, B> {
  //T apply(A a, B b);

  Optional<Tuple2<A, B>> unapply(T t);

  Class getExtractorClass();

  default <C> Extractor2<T, A, C> decomposeSecond(Extractor1<B, C> after) {
    Objects.requireNonNull(after);

    return new Extractor2<T, A, C>() {
      @Override
      public Optional<Tuple2<A, C>> unapply(T t) {
        Optional<Tuple2<A, B>> resultOpt = Extractor2.this.unapply(t);
        if (!resultOpt.isPresent()) {
          return Optional.empty();
        }

        Tuple2<A, B> result = resultOpt.get();
        Optional<C> nestedResultOpt = after.unapply(result.second());
        if (!nestedResultOpt.isPresent()) {
          return Optional.empty();
        }

        return Optional.of(Tuple2.of(result.first(), nestedResultOpt.get()));
      }

      @Override
      public Class getExtractorClass() {
        return Extractor2.this.getExtractorClass();
      }
    };
  }

  default <C> Extractor2<T, C, B> decomposeFirst(Extractor1<A, C> after) {
    return new Extractor2<T, C, B>() {
      @Override
      public Optional<Tuple2<C, B>> unapply(T t) {
        Optional<Tuple2<A, B>> resultOpt = Extractor2.this.unapply(t);
        if (!resultOpt.isPresent()) {
          return Optional.empty();
        }

        Tuple2<A, B> result = resultOpt.get();
        Optional<C> nestedResultOpt = after.unapply(result.first());
        if (!nestedResultOpt.isPresent()) {
          return Optional.empty();
        }

        return Optional.of(Tuple2.of(nestedResultOpt.get(), result.second()));
      }

      @Override
      public Class getExtractorClass() {
        return Extractor2.this.getExtractorClass();
      }
    };
  }

  default <C, D> Extractor3<T, C, D, B> decomposeFirst(Extractor2<A, C, D> after) {
    Objects.requireNonNull(after);

    return new Extractor3<T, C, D, B>() {
      @Override
      public Optional<Tuple3<C, D, B>> unapply(T t) {
        Optional<Tuple2<A, B>> resultOpt = Extractor2.this.unapply(t);
        if (!resultOpt.isPresent()) {
          return Optional.empty();
        }

        Tuple2<A, B> result = resultOpt.get();
        Optional<Tuple2<C, D>> nestedResultOpt = after.unapply(result.first());
        if (!nestedResultOpt.isPresent()) {
          return Optional.empty();
        }

        Tuple2<C, D> nestedResult = nestedResultOpt.get();
        return Optional.of(Tuple3.of(nestedResult.first(), nestedResult.second(), result.second()));
      }

      @Override
      public Class getExtractorClass() {
        return Extractor2.this.getExtractorClass();
      }
    };
  }

  default <C, D> Extractor3<T, A, C, D> decomposeSecond(Extractor2<B, C, D> after) {
    Objects.requireNonNull(after);

    return new Extractor3<T, A, C, D>() {
      @Override
      public Optional<Tuple3<A, C, D>> unapply(T t) {
        Optional<Tuple2<A, B>> resultOpt = Extractor2.this.unapply(t);
        if (!resultOpt.isPresent()) {
          return Optional.empty();
        }

        Tuple2<A, B> result = resultOpt.get();
        Optional<Tuple2<C, D>> nestedResultOpt = after.unapply(result.second());
        if (!nestedResultOpt.isPresent()) {
          return Optional.empty();
        }

        Tuple2<C, D> nestedResult = nestedResultOpt.get();
        return Optional.of(Tuple3.of(result.first(), nestedResult.first(), nestedResult.second()));
      }

      @Override
      public Class getExtractorClass() {
        return Extractor2.this.getExtractorClass();
      }
    };
  }
}
