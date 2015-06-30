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

import com.leacox.motif.extract.DecomposableMatchBuilder0;
import com.leacox.motif.extract.DecomposableMatchBuilder1;
import com.leacox.motif.MatchesAny;
import com.leacox.motif.extract.Extractor1;
import com.leacox.motif.extract.FieldExtractor;

import com.leacox.motif.extract.matchers.Matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class PrimitiveCases {
  private PrimitiveCases() {
  }

  private static class PrimitiveExtractor<T> implements Extractor1<T, T> {
    private final Class<T> primitiveType;

    PrimitiveExtractor(Class<T> primitiveType) {
      this.primitiveType = primitiveType;
    }

    @Override
    public Optional<T> unapply(T t) {
      return Optional.ofNullable(t);
    }

    @Override
    public Class getExtractorClass() {
      return primitiveType;
    }
  }

  private static class PrimitiveFieldsExtractor<T> implements FieldExtractor<T> {
    private final PrimitiveExtractor<T> primitiveExtractor;

    PrimitiveFieldsExtractor(Class<T> primitiveType) {
      this.primitiveExtractor = new PrimitiveExtractor<>(primitiveType);
    }

    @Override
    public Optional<List<Object>> unapply(T t) {
      Optional<T> opt = primitiveExtractor.unapply(t);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class getExtractorClass() {
      return primitiveExtractor.getExtractorClass();
    }
  }

  public static DecomposableMatchBuilder0<Byte> caseByte(byte b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(b));

    return new DecomposableMatchBuilder0<>(matchers, new PrimitiveFieldsExtractor<>(Byte.class));
  }

  public static DecomposableMatchBuilder1<Byte, Byte> caseByte(MatchesAny b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 0, new PrimitiveFieldsExtractor<>(Byte.class));
  }

  public static DecomposableMatchBuilder0<Short> caseShort(short s) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(s));

    return new DecomposableMatchBuilder0<>(matchers, new PrimitiveFieldsExtractor<>(Short.class));
  }

  public static DecomposableMatchBuilder1<Short, Short> caseShort(MatchesAny s) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(
        matchers, 0, new PrimitiveFieldsExtractor<>(Short.class));
  }

  public static DecomposableMatchBuilder0<Integer> caseInt(int i) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(i));

    return new DecomposableMatchBuilder0<>(matchers, new PrimitiveFieldsExtractor<>(Integer.class));
  }

  public static DecomposableMatchBuilder1<Integer, Integer> caseInt(MatchesAny i) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(
        matchers, 0, new PrimitiveFieldsExtractor<>(Integer.class));
  }

  public static DecomposableMatchBuilder0<Long> caseLong(long l) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(l));

    return new DecomposableMatchBuilder0<>(matchers, new PrimitiveFieldsExtractor<>(Long.class));
  }

  public static DecomposableMatchBuilder1<Long, Long> caseLong(MatchesAny b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 0, new PrimitiveFieldsExtractor<>(Long.class));
  }

  public static DecomposableMatchBuilder0<Float> caseFloat(float f) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(f));

    return new DecomposableMatchBuilder0<>(matchers, new PrimitiveFieldsExtractor<>(Float.class));
  }

  public static DecomposableMatchBuilder1<Float, Float> caseFloat(MatchesAny F) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(
        matchers, 0, new PrimitiveFieldsExtractor<>(Float.class));
  }

  public static DecomposableMatchBuilder0<Double> caseDouble(double d) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(d));

    return new DecomposableMatchBuilder0<>(matchers, new PrimitiveFieldsExtractor<>(Double.class));
  }

  public static DecomposableMatchBuilder1<Double, Double> caseDouble(MatchesAny d) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(
        matchers, 0, new PrimitiveFieldsExtractor<>(Double.class));
  }

  public static DecomposableMatchBuilder0<Character> caseChar(char c) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(c));

    return new DecomposableMatchBuilder0<>(
        matchers, new PrimitiveFieldsExtractor<>(Character.class));
  }

  public static DecomposableMatchBuilder1<Character, Character> caseChar(MatchesAny c) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(
        matchers, 0, new PrimitiveFieldsExtractor<>(Character.class));
  }

  public static DecomposableMatchBuilder0<String> caseString(String s) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(s));

    return new DecomposableMatchBuilder0<>(
        matchers, new PrimitiveFieldsExtractor<>(String.class));
  }

  public static DecomposableMatchBuilder1<String, String> caseString(MatchesAny s) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(
        matchers, 0, new PrimitiveFieldsExtractor<>(String.class));
  }

  public static DecomposableMatchBuilder0<Boolean> caseBoolean(boolean b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(b));

    return new DecomposableMatchBuilder0<>(
        matchers, new PrimitiveFieldsExtractor<>(Boolean.class));
  }

  public static DecomposableMatchBuilder1<Boolean, Boolean> caseBoolean(MatchesAny b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(
        matchers, 0, new PrimitiveFieldsExtractor<>(Boolean.class));
  }
}
