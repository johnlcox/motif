package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.matching.MatchingExtractor1;

import org.hamcrest.Matcher;

import java.util.Optional;

/**
 * @author John Leacox
 */
public class CaseThatCases {
  private static class IdentityExtractor<T> implements Extractor1<T, T> {
    @Override
    public Optional<T> unapply(T t) {
      return Optional.ofNullable(t);
    }

    @Override
    public Class getExtractorClass() {
      return Object.class;
    }
  }

  public static <T> MatchingExtractor1<T, T> caseThat(Matcher<T> matcher) {
    return MatchingExtractor1.create(new IdentityExtractor<>(), matcher);
  }

  public static <T> MatchingExtractor1<T, T> caseEq(T o) {
    return caseThat(eq(o));
  }
}
