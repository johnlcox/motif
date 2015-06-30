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
package com.leacox.motif.hamcrest;

import static org.hamcrest.CoreMatchers.equalTo;

import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.extract.Extractor1;
import com.leacox.motif.extract.FieldExtractor;
import com.leacox.motif.extract.matchers.Matcher;
import com.leacox.motif.extract.util.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public class CaseThatCases {
  private static class IdentityExtractor<T> implements Extractor1<T, T> {
    @Override
    public Optional<T> unapply(T t) {
      return Optional.ofNullable(t);
    }

    @Override
    public Class getExtractorClass() {
      return Object.class;
    }
  }

  private static class IdentityFieldExtractor<T> implements FieldExtractor<T> {
    private final IdentityExtractor<T> identityExtractor = new IdentityExtractor<>();

    @Override
    public Optional<List<Object>> unapply(T value) {
      Optional<T> opt = identityExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return identityExtractor.getExtractorClass();
    }
  }

  private static class HamcrestMatcher<T> implements Matcher<T> {
    private final org.hamcrest.Matcher<T> matcher;

    private HamcrestMatcher(org.hamcrest.Matcher<T> matcher) {
      this.matcher = matcher;
    }

    static <T> HamcrestMatcher<T> of(org.hamcrest.Matcher<T> matcher) {
      return new HamcrestMatcher<>(matcher);
    }

    @Override
    public boolean matches(Object arg) {
      return matcher.matches(arg);
    }
  }

  public static <T> DecomposableMatchBuilder1<T, T> caseThat(org.hamcrest.Matcher<T> matcher) {
    @SuppressWarnings("unchecked")
    List<Matcher<Object>> matchers = Lists.of((Matcher<Object>) HamcrestMatcher.of(matcher));

    return new DecomposableMatchBuilder1<>(matchers, 0, new IdentityFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<T, T> caseEq(T o) {
    return caseThat(equalTo(o));
  }
}
