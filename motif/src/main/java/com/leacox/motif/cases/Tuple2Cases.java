package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.extraction.DecomposableMatchBuilder0;
import com.leacox.motif.extraction.DecomposableMatchBuilder1;
import com.leacox.motif.extraction.DecomposableMatchBuilder2;
import com.leacox.motif.extraction.Extractor2;
import com.leacox.motif.extraction.FieldExtractor;
import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.util.Lists;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class Tuple2Cases {
  private Tuple2Cases() {
  }

  private static class Tuple2Extractor<A, B> implements Extractor2<Tuple2<A, B>, A, B> {
    @Override
    public Optional<Tuple2<A, B>> unapply(Tuple2<A, B> tuple2) {
      return Optional.ofNullable(tuple2);
    }

    @Override
    public Class<Tuple2> getExtractorClass() {
      return Tuple2.class;
    }
  }

  private static class Tuple2FieldExtractor<A, B> implements FieldExtractor<Tuple2<A, B>> {
    Tuple2Extractor<A, B> tuple2Extractor = new Tuple2Extractor<>();

    @Override
    public Optional<List<Object>> unapply(Tuple2<A, B> value) {
      Optional<Tuple2<A, B>> tuple2Opt = tuple2Extractor.unapply(value);
      if (!tuple2Opt.isPresent()) {
        return Optional.empty();
      }

      return Optional.of(value.toList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return tuple2Extractor.getExtractorClass();
    }
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(A a, B b) {
    List<Matcher<Object>> matchers = Lists.of(eq(a), eq(b));

    return new DecomposableMatchBuilder0<>(matchers, new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, A> tuple2(MatchesAny a, B b) {
    List<Matcher<Object>> matchers = Lists.of(any(), eq(b));

    return new DecomposableMatchBuilder1<>(matchers, 0, new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, B> tuple2(A a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(eq(a), any());

    return new DecomposableMatchBuilder1<>(matchers, 1, new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder2<Tuple2<A, B>, A, B> tuple2(
      MatchesAny a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder2<>(matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(
      DecomposableMatchBuilder0<A> a, B b) {
    List<Matcher<Object>> matchers = Lists.of(any(), eq(b));

    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(matchers, 0, new Tuple2FieldExtractor<>())
        .decomposeFirst(a);
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(
      A a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(eq(a), any());

    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(matchers, 1, new Tuple2FieldExtractor<>())
        .decomposeFirst(b);
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple2<A, B>, C> tuple2(
      DecomposableMatchBuilder1<A, C> a, B b) {
    List<Matcher<Object>> matchers = Lists.of(any(), eq(b));

    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(matchers, 0, new Tuple2FieldExtractor<>())
        .decomposeFirst(a);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple2<A, B>, C> tuple2(
      A a, DecomposableMatchBuilder1<B, C> b) {
    List<Matcher<Object>> matchers = Lists.of(eq(a), any());

    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(matchers, 1, new Tuple2FieldExtractor<>())
        .decomposeFirst(b);
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, A> tuple2(
      MatchesAny a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, B> tuple2(
      DecomposableMatchBuilder0<A> a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple2<A, B>, C> tuple2(
      DecomposableMatchBuilder1<A, C> a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(
        matchers, 0, new Tuple2FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple2<A, B>, C> tuple2(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, C> b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(
        matchers, 1, new Tuple2FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple2<A, B>, A, C> tuple2(
      MatchesAny a, DecomposableMatchBuilder1<B, C> b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple2<A, B>, C, B> tuple2(
      DecomposableMatchBuilder1<A, C> a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, D> DecomposableMatchBuilder2<Tuple2<A, B>, C, D> tuple2(
      DecomposableMatchBuilder1<A, C> a, DecomposableMatchBuilder1<B, D> b) {
    List<Matcher<Object>> matchers = Lists.of(any(), any());

    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }
}
