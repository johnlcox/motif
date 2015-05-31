package com.leacox.motif.example.superhero;

import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.tuple.Tuple2;

import com.google.auto.value.AutoValue;

import java.math.BigDecimal;

/**
 * @author John Leacox
 */
@AutoValue
public abstract class Civilian implements Case2<String, BigDecimal>, Character {
  public static Civilian create(String name, BigDecimal wealth) {
    return new AutoValue_Civilian(name, wealth);
  }

  @Override
  public Tuple2<String, BigDecimal> extract() {
    return Tuple2.of(name(), wealth());
  }

  public abstract String name();

  public abstract BigDecimal wealth();
}
