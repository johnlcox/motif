/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leacox.motif.cases;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.DecomposableMatchBuilder3;
import com.leacox.motif.extract.matchers.ArgumentMatchers;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;
import com.leacox.motif.tuple.Tuple2;

import java.util.List;

public final class ListConsCases {
  private ListConsCases() {
  }

  public static <T> DecomposableMatchBuilder0<List<T>> nil() {
    List<Matcher<Object>> matchers = Lists.of();
    return new DecomposableMatchBuilder0<List<T>>(matchers, new ListConsNilFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder0<List<T>> headNil(T head) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(head));
    return new DecomposableMatchBuilder0<List<T>>(matchers, new ListConsHeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headNil(MatchesAny head) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder0<List<T>> headNil(DecomposableMatchBuilder0<T> head) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1> DecomposableMatchBuilder1<List<T>, A1> headNil(
      DecomposableMatchBuilder1<T, A1> head) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1, A2> DecomposableMatchBuilder2<List<T>, A1, A2> headNil(
      DecomposableMatchBuilder2<T, A1, A2> head) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1, A2, A3> DecomposableMatchBuilder3<List<T>, A1, A2, A3> headNil(
      DecomposableMatchBuilder3<T, A1, A2, A3> head) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T> DecomposableMatchBuilder0<List<T>> headTail(T head, List<T> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(head), ArgumentMatchers.eq(tail));
    return new DecomposableMatchBuilder0<List<T>>(matchers, new ListConsHeadTailFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, List<T>> headTail(T head, MatchesAny tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(head), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, List<T>>(
        matchers, 1, new ListConsHeadTailFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder0<List<T>> headTail(
      T head, DecomposableMatchBuilder0<List<T>> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(head), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, List<T>>(
        matchers, 1, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(tail);
  }

  public static <T, B1> DecomposableMatchBuilder1<List<T>, B1> headTail(
      T head, DecomposableMatchBuilder1<List<T>, B1> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(head), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, List<T>>(
        matchers, 1, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(tail);
  }

  public static <T, B1, B2> DecomposableMatchBuilder2<List<T>, B1, B2> headTail(
      T head, DecomposableMatchBuilder2<List<T>, B1, B2> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(head), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, List<T>>(
        matchers, 1, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(tail);
  }

  public static <T, B1, B2, B3> DecomposableMatchBuilder3<List<T>, B1, B2, B3> headTail(
      T head, DecomposableMatchBuilder3<List<T>, B1, B2, B3> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.eq(head), ArgumentMatchers.any());
    return new DecomposableMatchBuilder1<List<T>, List<T>>(
        matchers, 1, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(tail);
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headTail(MatchesAny head, List<T> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(tail));
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadTailFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder2<List<T>, T, List<T>> headTail(
      MatchesAny head, MatchesAny tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headTail(
      MatchesAny head, DecomposableMatchBuilder0<List<T>> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>()).decomposeSecond(tail);
  }

  public static <T, B1> DecomposableMatchBuilder2<List<T>, T, B1> headTail(
      MatchesAny head, DecomposableMatchBuilder1<List<T>, B1> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>()).decomposeSecond(tail);
  }

  public static <T, B1, B2> DecomposableMatchBuilder3<List<T>, T, B1, B2> headTail(
      MatchesAny head, DecomposableMatchBuilder2<List<T>, B1, B2> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>()).decomposeSecond(tail);
  }

  public static <T> DecomposableMatchBuilder0<List<T>> headTail(
      DecomposableMatchBuilder0<T> head, List<T> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(tail));
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T> DecomposableMatchBuilder1<List<T>, List<T>> headTail(
      DecomposableMatchBuilder0<T> head, MatchesAny tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T> DecomposableMatchBuilder0<List<T>> headTail(
      DecomposableMatchBuilder0<T> head, DecomposableMatchBuilder0<List<T>> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, B1> DecomposableMatchBuilder1<List<T>, B1> headTail(
      DecomposableMatchBuilder0<T> head, DecomposableMatchBuilder1<List<T>, B1> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, B1, B2> DecomposableMatchBuilder2<List<T>, B1, B2> headTail(
      DecomposableMatchBuilder0<T> head, DecomposableMatchBuilder2<List<T>, B1, B2> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, B1, B2, B3> DecomposableMatchBuilder3<List<T>, B1, B2, B3> headTail(
      DecomposableMatchBuilder0<T> head, DecomposableMatchBuilder3<List<T>, B1, B2, B3> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, A1> DecomposableMatchBuilder1<List<T>, A1> headTail(
      DecomposableMatchBuilder1<T, A1> head, List<T> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(tail));
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1> DecomposableMatchBuilder2<List<T>, A1, List<T>> headTail(
      DecomposableMatchBuilder1<T, A1> head, MatchesAny tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1> DecomposableMatchBuilder1<List<T>, A1> headTail(
      DecomposableMatchBuilder1<T, A1> head, DecomposableMatchBuilder0<List<T>> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, A1, B1> DecomposableMatchBuilder2<List<T>, A1, B1> headTail(
      DecomposableMatchBuilder1<T, A1> head, DecomposableMatchBuilder1<List<T>, B1> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, A1, B1, B2> DecomposableMatchBuilder3<List<T>, A1, B1, B2> headTail(
      DecomposableMatchBuilder1<T, A1> head, DecomposableMatchBuilder2<List<T>, B1, B2> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, A1, A2> DecomposableMatchBuilder2<List<T>, A1, A2> headTail(
      DecomposableMatchBuilder2<T, A1, A2> head, List<T> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(tail));
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1, A2> DecomposableMatchBuilder3<List<T>, A1, A2, List<T>> headTail(
      DecomposableMatchBuilder2<T, A1, A2> head, MatchesAny tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1, A2> DecomposableMatchBuilder2<List<T>, A1, A2> headTail(
      DecomposableMatchBuilder2<T, A1, A2> head, DecomposableMatchBuilder0<List<T>> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, A1, A2, B1> DecomposableMatchBuilder3<List<T>, A1, A2, B1> headTail(
      DecomposableMatchBuilder2<T, A1, A2> head, DecomposableMatchBuilder1<List<T>, B1> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }

  public static <T, A1, A2, A3> DecomposableMatchBuilder3<List<T>, A1, A2, A3> headTail(
      DecomposableMatchBuilder3<T, A1, A2, A3> head, List<T> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.eq(tail));
    return new DecomposableMatchBuilder1<List<T>, T>(
        matchers, 0, new ListConsHeadTailFieldExtractor<>()).decomposeFirst(head);
  }

  public static <T, A1, A2, A3> DecomposableMatchBuilder3<List<T>, A1, A2, A3> headTail(
      DecomposableMatchBuilder3<T, A1, A2, A3> head, DecomposableMatchBuilder0<List<T>> tail) {
    List<Matcher<Object>> matchers = Lists.of(ArgumentMatchers.any(), ArgumentMatchers.any());
    return new DecomposableMatchBuilder2<List<T>, T, List<T>>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>())
        .decomposeFirstAndSecond(head, tail);
  }
}
