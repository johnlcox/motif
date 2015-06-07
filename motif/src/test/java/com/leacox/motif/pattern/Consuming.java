package com.leacox.motif.pattern;

/**
 * A helper class for testing pattern matching with consumers.
 *
 * @author John Leacox
 */
class Consuming {
  private Object t;

  public Object getConsumed() {
    return t;
  }

  public void consume(Object t) {
    this.t = t;
  }
}
