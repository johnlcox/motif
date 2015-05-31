package com.leacox.motif.example.superhero;

import static com.leacox.motif.pattern.CaseClassPattern.case3;

import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.function.Function3;
import com.leacox.motif.matchers.ArgumentMatcher;
import com.leacox.motif.pattern.Pattern;
import com.leacox.motif.tuple.Tuple3;

import com.google.auto.value.AutoValue;

import java.util.List;
import java.util.Optional;

import autovalue.shaded.com.google.common.common.collect.ImmutableList;

/**
 * @author John Leacox
 */
@AutoValue
public abstract class SuperHero
    implements Case3<String, ImmutableList<String>, Optional<Civilian>>, Character {
  public static SuperHero create(String name, List<String> powers, Optional<Civilian> alterEgo) {
    return new AutoValue_SuperHero(name, ImmutableList.copyOf(powers), alterEgo);
  }

  @Override
  public Tuple3<String, ImmutableList<String>, Optional<Civilian>> extract() {
    return Tuple3.of(name(), powers(), alterEgo());
  }

  public abstract String name();

  public abstract ImmutableList<String> powers();

  public abstract Optional<Civilian> alterEgo();

  public static <T extends Character, R> Pattern<T, R> caseSuperHero(
      ArgumentMatcher<String> name, ArgumentMatcher<ImmutableList<String>> powers,
      ArgumentMatcher<Optional<Civilian>> alterEgo,
      Function3<String, ImmutableList<String>, Optional<Civilian>, R> function) {
    return case3(SuperHero.class, name, powers, alterEgo, function);
  }
}
