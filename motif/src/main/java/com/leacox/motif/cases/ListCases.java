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

import static com.leacox.motif.extract.matchers.ArgumentMatchers.any;
import static com.leacox.motif.extract.matchers.ArgumentMatchers.eq;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author John Leacox
 */
public final class ListCases {
  private ListCases() {
  }

  public static <T> DecomposableMatchBuilder0<List<T>> nil() {
    return new DecomposableMatchBuilder0<>(
        Collections.emptyList(), new ListConsNilFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headNil(T head) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(head));

    return new DecomposableMatchBuilder1<>(matchers, 0, new ListConsHeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headNil(MatchesAny head) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 0, new ListConsHeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder2<List<T>, T, List<T>> headTail(
      T head, MatchesAny tail) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(head));
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder2<List<T>, T, List<T>> headTail(
      MatchesAny head, MatchesAny tail) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new ListConsHeadTailFieldExtractor<>());
  }
}
