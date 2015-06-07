package com.leacox.motif.fluent.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.fluent.MatchingExtractor1;
import com.leacox.motif.fluent.extractor.Extractor1;

import org.hamcrest.Matcher;

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

  public static MatchingExtractor1<Byte, Byte> caseByte(byte b) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Byte.class), eq(b));
  }

  public static MatchingExtractor1<Byte, Byte> caseByte(Matcher<Byte> b) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Byte.class), b);
  }

  public static MatchingExtractor1<Short, Short> caseShort(short s) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Short.class), eq(s));
  }

  public static MatchingExtractor1<Short, Short> caseShort(Matcher<Short> s) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Short.class), s);
  }

  public static MatchingExtractor1<Integer, Integer> caseInt(int i) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Integer.class), eq(i));
  }

  public static MatchingExtractor1<Integer, Integer> caseInt(Matcher<Integer> i) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Integer.class), i);
  }

  public static MatchingExtractor1<Long, Long> caseLong(long l) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Long.class), eq(l));
  }

  public static MatchingExtractor1<Long, Long> caseLong(Matcher<Long> l) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Long.class), l);
  }

  public static MatchingExtractor1<Float, Float> caseFloat(float f) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Float.class), eq(f));
  }

  public static MatchingExtractor1<Float, Float> caseFloat(Matcher<Float> f) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Float.class), f);
  }

  public static MatchingExtractor1<Double, Double> caseDouble(double d) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Double.class), eq(d));
  }

  public static MatchingExtractor1<Double, Double> caseDouble(Matcher<Double> d) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Double.class), d);
  }

  public static MatchingExtractor1<Character, Character> caseChar(char c) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Character.class), eq(c));
  }

  public static MatchingExtractor1<Character, Character> caseChar(Matcher<Character> c) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Character.class), c);
  }

  public static MatchingExtractor1<String, String> caseString(String s) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(String.class), eq(s));
  }

  public static MatchingExtractor1<String, String> caseString(Matcher<String> s) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(String.class), s);
  }

  public static MatchingExtractor1<Boolean, Boolean> caseBoolean(boolean b) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Boolean.class), eq(b));
  }

  public static MatchingExtractor1<Boolean, Boolean> caseBoolean(Matcher<Boolean> b) {
    return new MatchingExtractor1<>(new PrimitiveExtractor<>(Boolean.class), b);
  }
}
