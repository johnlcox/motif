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

import static com.leacox.motif.MatchesAny.any;
import static com.leacox.motif.MatchesExact.eq;
import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.Case2Cases.case2;
import static com.leacox.motif.cases.Case3Cases.case3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import autovalue.shaded.com.google.common.common.collect.ImmutableList;

/**
 * @author John Leacox
 */
public class SuperHeroExample {
  public static void main(String[] args) {
    new SuperHeroExample().run();
  }

  public void run() {
    List<String> powers = new ArrayList<>();
    powers.add("Awesome Suit");
    powers.add("Flying");

    Civilian tonyStark = Civilian.create("Tony Stark", new BigDecimal(9838439287473l));

    Character ironMan = SuperHero.create("IronMan", powers, Optional.of(tonyStark));

    Optional<ImmutableList<String>> ironManPowersOpt = getPowersForAlterEgo(ironMan, tonyStark);

    System.out.println("Iron man powers: " + ironManPowersOpt.get());

    Civilian benUrich = Civilian.create("Ben Urich", new BigDecimal(15000));

    Optional<ImmutableList<String>> benUrichPowersOpt = getPowersForAlterEgo(ironMan, benUrich);

    System.out.println("Iron man powers if alter ego Ben Urich: " + benUrichPowersOpt);
  }

  private Optional<ImmutableList<String>> getPowersForAlterEgo(
      Character character, Civilian alterEgo) {

    // TODO: Figure out why this doesn't work
    return match(character)
        //.when(case2(Animal.class, eq("Cat"), eq(4)))
        //.when(case3(SuperHero.class, any(), any(), eq(Optional.of(alterEgo)))).get(
        .when(case2(Civilian.class, any(), any())).get((n, w) -> Optional.<ImmutableList<String>>empty())
        .when(case3(SuperHero.class, eq(""), any(), eq(Optional.of(alterEgo)))).get(
            p -> Optional.of(p))
        .orElse(Optional.empty())
        .getMatch();
  }
}
