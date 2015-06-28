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
