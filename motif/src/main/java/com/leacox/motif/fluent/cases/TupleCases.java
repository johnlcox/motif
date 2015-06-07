package com.leacox.motif.fluent.cases;

import com.leacox.motif.fluent.MatchingExtractor1;
import com.leacox.motif.fluent.MatchingExtractor2;
import com.leacox.motif.fluent.MatchingExtractor3;
import com.leacox.motif.fluent.extractor.Extractor1;
import com.leacox.motif.fluent.extractor.Extractor2;
import com.leacox.motif.fluent.extractor.Extractor3;
import com.leacox.motif.tuple.Tuple1;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import org.hamcrest.Matcher;

import java.util.Optional;

/**
 * @author John Leacox
 */
public final class TupleCases {
  private TupleCases() {
  }

  private static class Tuple1Extractor<A> implements Extractor1<Tuple1<A>, A> {
    //@Override
    //public Tuple1<A> apply(A a) {
    //  return Tuple1.of(a);
    //}

    @Override
    public Optional<A> unapply(Tuple1<A> tuple1) {
      return tuple1.first() == null ? Optional.empty() : Optional.ofNullable(tuple1.first());
    }
  }

  private static class Tuple2Extractor<A, B> implements Extractor2<Tuple2<A, B>, A, B> {
    //@Override
    //public Tuple2<A, B> apply(A a, B b) {
    //  return Tuple2.of(a, b);
    //}

    @Override
    public Optional<Tuple2<A, B>> unapply(Tuple2<A, B> tuple2) {
      return Optional.ofNullable(tuple2);
    }
  }

  private static class Tuple3Extractor<A, B, C> implements Extractor3<Tuple3<A, B, C>, A, B, C> {
    //@Override
    //public Tuple3<A, B, C> apply(A a, B b, C c) {
    //  return Tuple3.of(a, b, c);
    //}

    @Override
    public Optional<Tuple3<A, B, C>> unapply(Tuple3<A, B, C> tuple3) {
      return Optional.ofNullable(tuple3);
    }
  }

  public static <A> MatchingExtractor1<Tuple1<A>, A> caseTuple1(Matcher<A> first) {
    return new MatchingExtractor1<>(new Tuple1Extractor<>(), first);
  }

  public static <A, B> MatchingExtractor2<Tuple2<A, B>, A, B> caseTuple2(
      Matcher<A> first, Matcher<B> second) {
    return new MatchingExtractor2<>(new Tuple2Extractor<>(), first, second);
  }

  public static <A, B, C> MatchingExtractor3<Tuple3<A, B, C>, A, B, C> caseTuple3(
      Matcher<A> first, Matcher<B> second, Matcher<C> third) {
    return new MatchingExtractor3<>(new Tuple3Extractor<>(), first, second, third);
  }
}
