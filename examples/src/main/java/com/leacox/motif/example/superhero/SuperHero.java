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

import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.tuple.Tuple3;

import com.google.auto.value.AutoValue;

import java.util.List;
import java.util.Optional;

/**
 * A superhero character.
 *
 * @author John Leacox
 */
@AutoValue
public abstract class SuperHero
    implements Case3<String, List<String>, Optional<Civilian>>, Character {
  /**
   * Creates a new instance of {@link SuperHero}.
   */
  public static SuperHero create(String name, List<String> powers, Optional<Civilian> alterEgo) {
    return new AutoValue_SuperHero(name, powers, alterEgo);
  }

  @Override
  public Tuple3<String, List<String>, Optional<Civilian>> extract() {
    return Tuple3.of(name(), powers(), alterEgo());
  }

  public abstract String name();

  public abstract List<String> powers();

  public abstract Optional<Civilian> alterEgo();
}
