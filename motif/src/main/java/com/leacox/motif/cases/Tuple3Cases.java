package com.leacox.motif.cases;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.DecomposableMatchBuilder3;
import com.leacox.motif.extract.matchers.ArgumentMatchers;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;
import com.leacox.motif.extract.util.Lists;

import java.util.List;

public final class Tuple3Cases {
  private Tuple3Cases() {
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(A a, B b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.eq(b), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder0<Tuple3<A, B, C>>(matchers, new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, C> tuple3(
      A a, B b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, C>(
        matchers, 2, new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(
      A a, B b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, C>(
        matchers, 2, new Tuple3FieldExtractor<>()).decomposeFirst(c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder1<Tuple3<A, B, C>, C1> tuple3(
      A a, B b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, C>(
        matchers, 2, new Tuple3FieldExtractor<>()).decomposeFirst(c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder2<Tuple3<A, B, C>, C1, C2> tuple3(
      A a, B b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, C>(
        matchers, 2, new Tuple3FieldExtractor<>()).decomposeFirst(c);
  }

  public static <A, B, C, C1, C2, C3> DecomposableMatchBuilder3<Tuple3<A, B, C>, C1, C2, C3> tuple3(
      A a, B b, DecomposableMatchBuilder3<C, C1, C2, C3> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, C>(
        matchers, 2, new Tuple3FieldExtractor<>()).decomposeFirst(c);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, B> tuple3(
      A a, MatchesAny b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, B>(
        matchers, 1, new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C> tuple3(
      A a, MatchesAny b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, B> tuple3(
      A a, MatchesAny b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeSecond(c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C1> tuple3(
      A a, MatchesAny b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeSecond(c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, B, C1, C2> tuple3(
      A a, MatchesAny b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeSecond(c);
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(
      A a, DecomposableMatchBuilder0<B> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, B>(
        matchers, 1, new Tuple3FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, C> tuple3(
      A a, DecomposableMatchBuilder0<B> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(
      A a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder1<Tuple3<A, B, C>, C1> tuple3(
      A a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder2<Tuple3<A, B, C>, C1, C2> tuple3(
      A a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, C1, C2, C3> DecomposableMatchBuilder3<Tuple3<A, B, C>, C1, C2, C3> tuple3(
      A a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder3<C, C1, C2, C3> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder1<Tuple3<A, B, C>, B1> tuple3(
      A a, DecomposableMatchBuilder1<B, B1> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, B>(
        matchers, 1, new Tuple3FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, C> tuple3(
      A a, DecomposableMatchBuilder1<B, B1> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder1<Tuple3<A, B, C>, B1> tuple3(
      A a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, B1, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, C1> tuple3(
      A a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, B1, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, C1, C2> tuple3(
      A a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, B2> tuple3(
      A a, DecomposableMatchBuilder2<B, B1, B2> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, B>(
        matchers, 1, new Tuple3FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, C> tuple3(
      A a, DecomposableMatchBuilder2<B, B1, B2> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, B2> tuple3(
      A a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, B1, B2, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, C1> tuple3(
      A a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C, B1, B2, B3> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, B3> tuple3(
      A a, DecomposableMatchBuilder3<B, B1, B2, B3> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, B>(
        matchers, 1, new Tuple3FieldExtractor<>()).decomposeFirst(b);
  }

  public static <A, B, C, B1, B2, B3> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, B3> tuple3(
      A a, DecomposableMatchBuilder3<B, B1, B2, B3> b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.eq(a), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C>(
        matchers, Tuple2.of(1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(b, c);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, A> tuple3(
      MatchesAny a, B b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, A>(
        matchers, 0, new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C> tuple3(
      MatchesAny a, B b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, A> tuple3(
      MatchesAny a, B b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeSecond(c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C1> tuple3(
      MatchesAny a, B b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeSecond(c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, C1, C2> tuple3(
      MatchesAny a, B b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeSecond(c);
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B> tuple3(
      MatchesAny a, MatchesAny b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C> tuple3(
      MatchesAny a, MatchesAny b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>());
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B> tuple3(
      MatchesAny a, MatchesAny b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeThird(c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C1> tuple3(
      MatchesAny a, MatchesAny b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeThird(c);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, A> tuple3(
      MatchesAny a, DecomposableMatchBuilder0<B> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C> tuple3(
      MatchesAny a, DecomposableMatchBuilder0<B> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, A> tuple3(
      MatchesAny a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecondAndThird(b, c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C1> tuple3(
      MatchesAny a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecondAndThird(b, c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, C1, C2> tuple3(
      MatchesAny a, DecomposableMatchBuilder0<B> b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecondAndThird(b, c);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B1> tuple3(
      MatchesAny a, DecomposableMatchBuilder1<B, B1> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B1, C> tuple3(
      MatchesAny a, DecomposableMatchBuilder1<B, B1> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B1> tuple3(
      MatchesAny a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecondAndThird(b, c);
  }

  public static <A, B, C, B1, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B1, C1> tuple3(
      MatchesAny a, DecomposableMatchBuilder1<B, B1> b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecondAndThird(b, c);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B1, B2> tuple3(
      MatchesAny a, DecomposableMatchBuilder2<B, B1, B2> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeSecond(b);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B1, B2> tuple3(
      MatchesAny a, DecomposableMatchBuilder2<B, B1, B2> b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeSecondAndThird(b, c);
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(
      DecomposableMatchBuilder0<A> a, B b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, A>(
        matchers, 0, new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, C> tuple3(
      DecomposableMatchBuilder0<A> a, B b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(
      DecomposableMatchBuilder0<A> a, B b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder1<Tuple3<A, B, C>, C1> tuple3(
      DecomposableMatchBuilder0<A> a, B b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder2<Tuple3<A, B, C>, C1, C2> tuple3(
      DecomposableMatchBuilder0<A> a, B b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, C1, C2, C3> DecomposableMatchBuilder3<Tuple3<A, B, C>, C1, C2, C3> tuple3(
      DecomposableMatchBuilder0<A> a, B b, DecomposableMatchBuilder3<C, C1, C2, C3> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, B> tuple3(
      DecomposableMatchBuilder0<A> a, MatchesAny b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C> DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C> tuple3(
      DecomposableMatchBuilder0<A> a, MatchesAny b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, B> tuple3(
      DecomposableMatchBuilder0<A> a, MatchesAny b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndThird(a, c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, B, C1> tuple3(
      DecomposableMatchBuilder0<A> a, MatchesAny b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndThird(a, c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, B, C1, C2> tuple3(
      DecomposableMatchBuilder0<A> a, MatchesAny b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndThird(a, c);
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C> DecomposableMatchBuilder1<Tuple3<A, B, C>, C> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C> DecomposableMatchBuilder0<Tuple3<A, B, C>> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, C1> DecomposableMatchBuilder1<Tuple3<A, B, C>, C1> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, C1, C2> DecomposableMatchBuilder2<Tuple3<A, B, C>, C1, C2> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, C1, C2, C3> DecomposableMatchBuilder3<Tuple3<A, B, C>, C1, C2, C3> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder3<C, C1, C2, C3> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder1<Tuple3<A, B, C>, B1> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, C> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, B1> DecomposableMatchBuilder1<Tuple3<A, B, C>, B1> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, B1, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, C1> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, B1, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, C1, C2> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, B2> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, C> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, B1, B2> DecomposableMatchBuilder2<Tuple3<A, B, C>, B1, B2> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, B1, B2, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, C1> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder2<B, B1, B2> b,
      DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, B1, B2, B3> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, B3> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder3<B, B1, B2, B3> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, B1, B2, B3> DecomposableMatchBuilder3<Tuple3<A, B, C>, B1, B2, B3> tuple3(
      DecomposableMatchBuilder0<A> a, DecomposableMatchBuilder3<B, B1, B2, B3> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder1<Tuple3<A, B, C>, A1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, B b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, A>(
        matchers, 0, new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, C> tuple3(
      DecomposableMatchBuilder1<A, A1> a, B b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder1<Tuple3<A, B, C>, A1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, B b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, A1, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, C1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, B b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, A1, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, C1, C2> tuple3(
      DecomposableMatchBuilder1<A, A1> a, B b, DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, B> tuple3(
      DecomposableMatchBuilder1<A, A1> a, MatchesAny b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, B, C> tuple3(
      DecomposableMatchBuilder1<A, A1> a, MatchesAny b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, B> tuple3(
      DecomposableMatchBuilder1<A, A1> a, MatchesAny b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndThird(a, c);
  }

  public static <A, B, C, A1, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, B, C1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, MatchesAny b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndThird(a, c);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder1<Tuple3<A, B, C>, A1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, C> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1> DecomposableMatchBuilder1<Tuple3<A, B, C>, A1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, C1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, C1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, C1, C2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, C1, C2> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder2<C, C1, C2> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, B1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, B1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1, B1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, B1, C> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1, B1> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, B1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, B1, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, B1, C1> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, B1, B2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, B1, B2> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder2<B, B1, B2> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1, B1, B2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, B1, B2> tuple3(
      DecomposableMatchBuilder1<A, A1> a, DecomposableMatchBuilder2<B, B1, B2> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, A2> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, B b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, A>(
        matchers, 0, new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, C> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, B b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, A2> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, B b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, A1, A2, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, C1> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, B b, DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, B> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, MatchesAny b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, B> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, MatchesAny b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndThird(a, c);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, A2> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, C> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b, MatchesAny c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1, A2> DecomposableMatchBuilder2<Tuple3<A, B, C>, A1, A2> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, A2, C1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, C1> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder1<C, C1> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, A2, B1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, B1> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder1<B, B1> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1, A2, B1> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, B1> tuple3(
      DecomposableMatchBuilder2<A, A1, A2> a, DecomposableMatchBuilder1<B, B1> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }

  public static <A, B, C, A1, A2, A3> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, A3> tuple3(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, B b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder1<Tuple3<A, B, C>, A>(
        matchers, 0, new Tuple3FieldExtractor<>()).decomposeFirst(a);
  }

  public static <A, B, C, A1, A2, A3> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, A3> tuple3(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, B b, DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(b), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, C>(
        matchers, Tuple2.of(0, 2), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, c);
  }

  public static <A, B, C, A1, A2, A3> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, A3> tuple3(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, DecomposableMatchBuilder0<B> b, C c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.eq(c));
    return new DecomposableMatchBuilder2<Tuple3<A, B, C>, A, B>(
        matchers, Tuple2.of(0, 1), new Tuple3FieldExtractor<>()).decomposeFirstAndSecond(a, b);
  }

  public static <A, B, C, A1, A2, A3> DecomposableMatchBuilder3<Tuple3<A, B, C>, A1, A2, A3> tuple3(
      DecomposableMatchBuilder3<A, A1, A2, A3> a, DecomposableMatchBuilder0<B> b,
      DecomposableMatchBuilder0<C> c) {
    List<Matcher<Object>> matchers =
        Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder3<Tuple3<A, B, C>, A, B, C>(
        matchers, Tuple3.of(0, 1, 2), new Tuple3FieldExtractor<>())
        .decomposeFirstAndSecondAndThird(a, b, c);
  }
}
