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
