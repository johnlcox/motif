package com.leacox.motif.tuple;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author John Leacox
 */
public abstract class Tuple {
  private final List<Object> values;

  protected Tuple(Object... values) {
    // TODO: Is it worth pulling in Guava to use ImmutableList?
    this.values = Collections.unmodifiableList(Arrays.asList(values));
  }

  public List<Object> toList() {
    return values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Tuple)) {
      return false;
    }
    Tuple tuple = (Tuple) o;
    return Objects.equals(values, tuple.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values);
  }

  @Override
  public String toString() {
    return "(" + values.stream().reduce((t, u) -> t + "," + u).get() + ")";
  }
}
