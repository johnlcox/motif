package com.leacox.motif.internal;

import com.leacox.motif.ArgumentMatcher;

import java.util.List;

/**
 * @author John Leacox
 */
public class ArgumentsComparator {
  private ArgumentsComparator() {
  }

  public static boolean argumentsMatch(ArgumentMatcher[] matchers, List<Object> args) {
    if (matchers.length != args.size()) {
      return false;
    }

    for (int i = 0; i < matchers.length; i++) {
      if (!matchers[i].matches(args.get(i))) {
        return false;
      }
    }

    return true;
  }
}
