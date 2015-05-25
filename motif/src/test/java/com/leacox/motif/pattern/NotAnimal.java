package com.leacox.motif.pattern;

import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.tuple.Tuple2;

import com.google.auto.value.AutoValue;

/**
 * @author John Leacox
 */
@AutoValue
public abstract class NotAnimal implements Case2<String, Integer> {

  public static NotAnimal create(String notName, int notNumberOfLegs) {
    return new AutoValue_NotAnimal(notName, notNumberOfLegs);
  }

  public abstract String notName();

  public abstract int notNumberOfLegs();

  @Override
  public Tuple2<String, Integer> extract() {
    return Tuple2.of(notName(), notNumberOfLegs());
  }
}
