package com.leacox.motif.cases;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.extraction.DecomposableMatchBuilder0;
import com.leacox.motif.extraction.DecomposableMatchBuilder1;
import com.leacox.motif.extraction.DecomposableMatchBuilder2;
import com.leacox.motif.extraction.DecomposableMatchBuilder3;
import com.leacox.motif.matchers.ArgumentMatchers;
import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.util.Lists;

import java.util.List;

public final class Tuple2Cases {
  private Tuple2Cases() {
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(A a, B b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.eq(b));
    return new DecomposableMatchBuilder0<Tuple2<A, B>>(matchers, new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, B> tuple2(A a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(
        matchers, 1, new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(
      A a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(matchers, 1, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            b);
  }

  public static <A, B, B1> DecomposableMatchBuilder1<Tuple2<A, B>, B1> tuple2(
      A a, DecomposableMatchBuilder1<B, B1> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(matchers, 1, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            b);
  }

  public static <A, B, B1, B2> DecomposableMatchBuilder2<Tuple2<A, B>, B1, B2> tuple2(
      A a, DecomposableMatchBuilder2<B, B1, B2> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(matchers, 1, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            b);
  }

  public static <A, B, B1, B2, B3> DecomposableMatchBuilder3<Tuple2<A, B>, B1, B2, B3> tuple2(
      A a, DecomposableMatchBuilder3<B, B1, B2, B3> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple2<A, B>, B>(matchers, 1, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            b);
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, A> tuple2(MatchesAny a, B b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b));
    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(
        matchers, 0, new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder2<Tuple2<A, B>, A, B> tuple2(
      MatchesAny a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>());
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, A> tuple2(
      MatchesAny a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, B1> DecomposableMatchBuilder2<Tuple2<A, B>, A, B1> tuple2(
      MatchesAny a, DecomposableMatchBuilder1<B, B1> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, B1, B2> DecomposableMatchBuilder3<Tuple2<A, B>, A, B1, B2> tuple2(
      MatchesAny a, DecomposableMatchBuilder2<B, B1, B2> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(
      DecomposableMatchBuilder0<A> a, B b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b));
    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(matchers, 0, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            a);
  }

  public static <A, B> DecomposableMatchBuilder1<Tuple2<A, B>, B> tuple2(
      DecomposableMatchBuilder0<A> a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B> DecomposableMatchBuilder0<Tuple2<A, B>> tuple2(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, B1> DecomposableMatchBuilder1<Tuple2<A, B>, B1> tuple2(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, B1, B2> DecomposableMatchBuilder2<Tuple2<A, B>, B1, B2> tuple2(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, B1, B2, B3> DecomposableMatchBuilder3<Tuple2<A, B>, B1, B2, B3> tuple2(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder3<B, B1, B2, B3> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, A1> DecomposableMatchBuilder1<Tuple2<A, B>, A1> tuple2(
      DecomposableMatchBuilder1<A, A1> a, B b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b));
    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(matchers, 0, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            a);
  }

  public static <A, B, A1> DecomposableMatchBuilder2<Tuple2<A, B>, A1, B> tuple2(
      DecomposableMatchBuilder1<A, A1> a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, A1> DecomposableMatchBuilder1<Tuple2<A, B>, A1> tuple2(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, A1, B1> DecomposableMatchBuilder2<Tuple2<A, B>, A1, B1> tuple2(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, A1, B1, B2> DecomposableMatchBuilder3<Tuple2<A, B>, A1, B1, B2> tuple2(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder2<B, B1, B2> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, A1, A2> DecomposableMatchBuilder2<Tuple2<A, B>, A1, A2> tuple2(
      DecomposableMatchBuilder2<A, A1, A2> a, B b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b));
    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(matchers, 0, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            a);
  }

  public static <A, B, A1, A2> DecomposableMatchBuilder3<Tuple2<A, B>, A1, A2, B> tuple2(
      DecomposableMatchBuilder2<A, A1, A2> a, MatchesAny b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, A1, A2> DecomposableMatchBuilder2<Tuple2<A, B>, A1, A2> tuple2(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, A1, A2, B1> DecomposableMatchBuilder3<Tuple2<A, B>, A1, A2, B1> tuple2(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder1<B, B1> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, A1, A2, A3> DecomposableMatchBuilder3<Tuple2<A, B>, A1, A2, A3> tuple2(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, B b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b));
    return new DecomposableMatchBuilder1<Tuple2<A, B>, A>(matchers, 0, new Tuple2FieldExtractor<>())
        .decomposeFirst(
            a);
  }

  public static <A, B, A1, A2, A3> DecomposableMatchBuilder3<Tuple2<A, B>, A1, A2, A3> tuple2(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, DecomposableMatchBuilder0<B> b) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple2<A, B>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple2FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }
}
