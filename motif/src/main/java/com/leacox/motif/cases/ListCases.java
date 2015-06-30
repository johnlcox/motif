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
import com.leacox.motif.extract.Extractor2;
import com.leacox.motif.extract.FieldExtractor;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class ListCases {
  private ListCases() {
  }

  private static class HeadExtractor<A> implements Extractor1<List<A>, A> {
    @Override
    public Optional<A> unapply(List<A> list) {
      if (list.size() == 1) {
        return Optional.of(list.get(0));
      }

      return Optional.empty();
    }

    @Override
    public Class<List> getExtractorClass() {
      return List.class;
    }
  }

  private static class HeadFieldExtractor<A> implements FieldExtractor<List<A>> {
    private final HeadExtractor<A> headExtractor = new HeadExtractor<>();

    @Override
    public Optional<List<Object>> unapply(List<A> value) {
      Optional<A> opt = headExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return headExtractor.getExtractorClass();
    }
  }


  private static class EmptyListExtractor<A> implements Extractor0<List<A>> {
    @Override
    public boolean unapply(List<A> list) {
      return list.isEmpty();
    }

    @Override
    public Class<List> getExtractorClass() {
      return List.class;
    }
  }

  private static class NilFieldExtractor<A> implements FieldExtractor<List<A>> {
    private final EmptyListExtractor<A> emptyListExtractor = new EmptyListExtractor<>();

    @Override
    public Optional<List<Object>> unapply(List<A> value) {
      if (!emptyListExtractor.unapply(value)) {
        return Optional.empty();
      }

      return Optional.of(Collections.emptyList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return emptyListExtractor.getExtractorClass();
    }
  }

  public static <T> DecomposableMatchBuilder0<List<T>> nil() {
    return new DecomposableMatchBuilder0<>(Collections.emptyList(), new NilFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headNil(T head) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(head));

    return new DecomposableMatchBuilder1<>(matchers, 0, new HeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headNil(MatchesAny head) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 0, new HeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder2<List<T>, T, List<T>> headTail(
      T head, MatchesAny tail) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(head));
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new HeadTailFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder2<List<T>, T, List<T>> headTail(
      MatchesAny head, MatchesAny tail) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new HeadTailFieldExtractor<>());
  }
}
