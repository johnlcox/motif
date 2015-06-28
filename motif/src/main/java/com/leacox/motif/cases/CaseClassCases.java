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

import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.extraction.DecomposableMatchBuilder0;
import com.leacox.motif.extraction.DecomposableMatchBuilder1;
import com.leacox.motif.extraction.DecomposableMatchBuilder2;
import com.leacox.motif.extraction.Extractor2;
import com.leacox.motif.extraction.Extractor3;
import com.leacox.motif.extraction.FieldExtractor;
import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;
import com.leacox.motif.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class CaseClassCases {
  private CaseClassCases() {
  }

  private static class Case2Extractor<T extends Case2<A, B>, A, B> implements Extractor2<T, A, B> {
    private final Class<T> caseClazz;

    Case2Extractor(Class<T> caseClazz) {
      this.caseClazz = caseClazz;
    }

    @Override
    public Optional<Tuple2<A, B>> unapply(T t) {
      return caseClazz.isAssignableFrom(t.getClass()) ? Optional.of(t.extract()) : Optional.empty();
    }

    @Override
    public Class getExtractorClass() {
      return caseClazz;
    }
  }

  private static class Case2FieldExtractor<T extends Case2<A, B>, A, B>
      implements FieldExtractor<T> {
    private final Case2Extractor<T, A, B> case2Extractor;

    Case2FieldExtractor(Class<T> caseClazz) {
      this.case2Extractor = new Case2Extractor<>(caseClazz);
    }

    @Override
    public Optional<List<Object>> unapply(T value) {
      Optional<Tuple2<A, B>> opt = case2Extractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      return Optional.of(opt.get().toList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return case2Extractor.getExtractorClass();
    }
  }

  private static class Case3Extractor<T extends Case3<A, B, C>, A, B, C>
      implements Extractor3<T, A, B, C> {
    private final Class<T> caseClazz;

    Case3Extractor(Class<T> caseClazz) {
      this.caseClazz = caseClazz;
    }

    //@Override
    //public Case3<A, B, C> apply(A a, B b, C c) {
    //  caseClazz.getConstructor(a.getClass(), b.getClass(), c.getClass());
    //
    //  return null;
    //}

    @Override
    public Optional<Tuple3<A, B, C>> unapply(T t) {
      if (!caseClazz.isAssignableFrom(t.getClass())) {
        return Optional.empty();
      }

      return Optional.of(t.extract());
    }

    @Override
    public Class getExtractorClass() {
      return caseClazz;
    }
  }

  private static class Case3FieldExtractor<T extends Case3<A, B, C>, A, B, C>
      implements FieldExtractor<T> {
    private final Case3Extractor<T, A, B, C> case3Extractor;

    Case3FieldExtractor(Class<T> caseClazz) {
      this.case3Extractor = new Case3Extractor<>(caseClazz);
    }

    @Override
    public Optional<List<Object>> unapply(T value) {
      Optional<Tuple3<A, B, C>> opt = case3Extractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      return Optional.of(opt.get().toList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return case3Extractor.getExtractorClass();
    }
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
