package com.leacox.motif;

/**
 * @author John Leacox
 */
public class MatchesAny<T> {
  private MatchesAny() {
  }

  private static MatchesAny ANY = new MatchesAny();

  public static MatchesAny __ = ANY;

  public static <T> MatchesAny<T> any() {
    return ANY;
  }

  public static <T> MatchesAny<T> any(Class<T> clazz) {
    return ANY;
  }
}
