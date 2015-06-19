package com.leacox.motif.decomposition;

/**
 * @author John Leacox
 */
public class MatchesAny {
  private MatchesAny() {
  }

  private static MatchesAny ANY = new MatchesAny();

  public static MatchesAny __ = ANY;

  public static MatchesAny any() {
    return ANY;
  }
}
