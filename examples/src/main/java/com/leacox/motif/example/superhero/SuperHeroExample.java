package com.leacox.motif.example.superhero;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.example.superhero.SuperHero.caseSuperHero;
import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;
import static com.leacox.motif.pattern.CaseClassPattern.case3;
import static com.leacox.motif.pattern.OrElsePattern.orElse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import autovalue.shaded.com.google.common.common.collect.ImmutableList;

/**
 * @author John Leacox
 */
public class SuperHeroExample {
  public Optional<ImmutableList<String>> getTonyStarksPowers() {
    List<String> powers = new ArrayList<>();
    powers.add("Awesome Suit");
    powers.add("Flying");

    Civilian tonyStark = Civilian.create("Tony Stark", new BigDecimal(9838439287473l));

    SuperHero ironMan = SuperHero.create("IronMan", powers, Optional.of(tonyStark));

    Character unknownPerson = ironMan;

    Optional<ImmutableList<String>> powersOpt = match(unknownPerson).on(
        caseSuperHero(any(), any(), eq(Optional.of(tonyStark)), (n, p, a) -> Optional.of(p)),
        orElse(Optional.<ImmutableList<String>>empty())
    );

    String notAPerson = "Hello";

    Optional<ImmutableList<String>> notAPersonPowersOpt = match(notAPerson).on(
        case3(
            SuperHero.class, any(), any(), eq(Optional.of(tonyStark)), (n, p, a) -> Optional.of(p)),
        orElse(Optional.<ImmutableList<String>>empty())
    );

    return powersOpt;
  }
}
