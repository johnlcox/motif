package com.leacox.motif.pattern;

import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongFunction;

/**
 * @author John Leacox
 */
public final class PrimitivePattern {
  private PrimitivePattern() {
  }

  public static <R> Pattern<Byte, R> caseByte(byte b, Function<Byte, R> function) {
    return Pattern.of(x -> x == b, function::apply);
  }

  public static <R> Pattern<Short, R> caseShort(short s, Function<Short, R> function) {
    return Pattern.of(x -> x == s, function::apply);
  }

  public static <R> Pattern<Integer, R> caseInt(int i, IntFunction<R> function) {
    return Pattern.of(x -> x == i, function::apply);
  }

  public static <R> Pattern<Long, R> caseLong(long l, LongFunction<R> function) {
    return Pattern.of(x -> x == l, function::apply);
  }

  public static <R> Pattern<Float, R> caseFloat(float f, Function<Float, R> function) {
    return Pattern.of(x -> x == f, function::apply);
  }

  public static <R> Pattern<Double, R> caseDouble(double d, DoubleFunction<R> function) {
    return Pattern.of(x -> x == d, function::apply);
  }

  public static <R> Pattern<Character, R> caseChar(char c, Function<Character, R> function) {
    return Pattern.of(x -> x == c, function::apply);
  }

  public static <R> Pattern<String, R> caseString(String s, Function<String, R> function) {
    return Pattern.of(x -> x.equals(s), function::apply);
  }

  public static <R> Pattern<Boolean, R> caseBoolean(boolean b, Function<Boolean, R> function) {
    return Pattern.of(x -> x == b, function::apply);
  }
}
