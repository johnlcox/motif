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
import com.leacox.motif.extract.Extractor0;
import com.leacox.motif.extract.Extractor1;
import com.leacox.motif.extract.FieldExtractor;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class OptionalCases {
  private OptionalCases() {
  }

  private static class OptionalExtractor<T> implements Extractor1<Optional<T>, T> {
    @Override
    public Optional<T> unapply(Optional<T> t) {
      if (t.isPresent()) {
        return t;
      } else {
        return Optional.empty();
      }
    }

    @Override
    public Class getExtractorClass() {
      return Optional.class;
    }
  }

  private static class NoneExtractor<T> implements Extractor0<Optional<T>> {
    @Override
    public boolean unapply(Optional<T> t) {
      return !t.isPresent();
    }

    @Override
    public Class<Optional> getExtractorClass() {
      return Optional.class;
    }
  }

  private static class OptionalFieldExtractor<T> implements FieldExtractor<Optional<T>> {
    OptionalExtractor<T> optionalExtractor = new OptionalExtractor<>();

    @Override
    public Optional<List<Object>> unapply(Optional<T> value) {
      Optional<T> opt = optionalExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return optionalExtractor.getExtractorClass();
    }
  }

  private static class NoneFieldExtractor<T> implements FieldExtractor<Optional<T>> {
    NoneExtractor<T> noneExtractor = new NoneExtractor<>();

    @Override
    public Optional<List<Object>> unapply(Optional<T> value) {
      if (!noneExtractor.unapply(value)) {
        return Optional.empty();
      }

      return Optional.of(Collections.emptyList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return noneExtractor.getExtractorClass();
    }
  }

  public static <T> DecomposableMatchBuilder0<Optional<T>> some(T a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(a));

    return new DecomposableMatchBuilder0<>(matchers, new OptionalFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<Optional<T>, T> some(MatchesAny<T> a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 0, new OptionalFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder0<Optional<T>> some(DecomposableMatchBuilder0<T> a) {
    List<Matcher<Object>> matchers = Lists.of(any());

    return new DecomposableMatchBuilder1<Optional<T>, T>(
        matchers, 0, new OptionalFieldExtractor<>()).decomposeFirst(a);
  }

  public static <T, A> DecomposableMatchBuilder1<Optional<T>, A> some(
      DecomposableMatchBuilder1<T, A> a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<Optional<T>, T>(
        matchers, 0, new OptionalFieldExtractor<>()).decomposeFirst(a);
  }

  public static <T, A, B> DecomposableMatchBuilder2<Optional<T>, A, B> some(
      DecomposableMatchBuilder2<T, A, B> a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<Optional<T>, T>(
        matchers, 0, new OptionalFieldExtractor<>()).decomposeFirst(a);
  }

  public static <T> DecomposableMatchBuilder0<Optional<T>> none() {
    return new DecomposableMatchBuilder0<>(Collections.emptyList(), new NoneFieldExtractor<>());
  }
}
