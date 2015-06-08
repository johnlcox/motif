package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.any;

import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.matching.MatchingExtractor1;

import java.util.Optional;

/**
 * @author John Leacox
 */
public final class TypeOfCases {
  private TypeOfCases() {
  }

  private static class TypeOfExtractor<S extends Object, T> implements Extractor1<S, T> {
    private final Class<T> expectedClass;

    TypeOfExtractor(Class<T> expectedClass) {
      this.expectedClass = expectedClass;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<T> unapply(Object o) {
      if (o == null) {
        return Optional.empty();
      }

      return expectedClass.isAssignableFrom(o.getClass()) ? Optional.of((T) o)
          : Optional.empty();
    }

    @Override
    public Class getExtractorClass() {
      return expectedClass;
    }
  }

  public static <S extends Object, T> MatchingExtractor1<S, T> caseTypeOf(Class<T> clazz) {
    return MatchingExtractor1.create(new TypeOfExtractor<>(clazz), any());
  }
}
