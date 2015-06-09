package com.leacox.motif;

/**
 * An exception that indicates that a match could not be found while executing the pattern matching
 * cases.
 *
 * <p>If this exception is thrown it means the matching cases weren't exhaustive. There may be a
 * case that needs to be added to the pattern matching that failed.
 *
 * @author John Leacox
 */
public class MatchException extends RuntimeException {
  public MatchException(String message) {
    super(message);
  }
}
