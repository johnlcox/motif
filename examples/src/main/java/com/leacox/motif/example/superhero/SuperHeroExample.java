package com.leacox.motif.example.superhero;

import static com.leacox.motif.Motif.match;
import static com.leacox.motif.cases.CaseClassCases.case3;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

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
        .when(case3(SuperHero.class, eq(""), eq(ImmutableList.of()), eq(Optional.of(alterEgo))))
        .get(
            (n, p, a) -> Optional.of(p))
        .orElse(Optional.empty())
        .getMatch();
  }
}
