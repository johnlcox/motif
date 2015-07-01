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
import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.DecomposableMatchBuilder2;
import com.leacox.motif.extract.Extractor2;
import com.leacox.motif.extract.Extractor3;
import com.leacox.motif.extract.FieldExtractor;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;
import com.leacox.motif.extract.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class CaseClassCases {
  private CaseClassCases() {
  }

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder0<T> case2(
      Class<T> clazz, A a, B b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(a));
    matchers.add(eq(b));

    return new DecomposableMatchBuilder0<>(matchers, new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder1<T, A> case2(
      Class<T> clazz, MatchesAny a, B b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());
    matchers.add(eq(b));

    return new DecomposableMatchBuilder1<>(matchers, 0, new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder1<T, B> case2(
      Class<T> clazz, A a, MatchesAny b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(a));
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 1, new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder2<T, A, B> case2(
      Class<T> clazz, MatchesAny a, MatchesAny b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case3<A, B, C>, A, B, C> DecomposableMatchBuilder0<T> case3(
      Class<T> clazz, A a, B b, C c) {
    List<Matcher<Object>> matchers = Lists.of(eq(a), eq(b));

    return new DecomposableMatchBuilder0<>(matchers, new Case3FieldExtractor<>(clazz));
  }

  //public static <T extends Case3<A, B, C>, A, B, C> DecomposableMatchBuilder1<T, A> case3(
  //    Class<T> clazz, MatchesAny a, B b, C c);

  public static <T extends Case3<A, B, C>, A, B, C> DecomposableMatchBuilder1<T, B> case3(
      Class<T> clazz, A a, MatchesAny b, C c) {
    List<Matcher<Object>> matchers = Lists.of(eq(a), any(), eq(c));

    return new DecomposableMatchBuilder1<>(matchers, 1, new Case3FieldExtractor<>(clazz));
  }

  //public static <T extends Case3<A, B, C>, A, B, C> DecomposableMatchBuilder0<T, C> case3(
  //    Class<T> clazz, A a, B b, MatchesAny c);
}
