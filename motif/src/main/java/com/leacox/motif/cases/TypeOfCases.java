package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.any;

import com.leacox.motif.decomposition.DecomposableMatchBuilder1;
import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.extractor.FieldExtractor;

import com.leacox.motif.matchers.Matcher;

import java.util.ArrayList;
import java.util.List;
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

  private static class TypeOfFieldExtractor<S, T> implements FieldExtractor<S> {
    private final TypeOfExtractor<S, T> typeOfExtractor;

    TypeOfFieldExtractor(Class<T> expectedClass) {
      this.typeOfExtractor = new TypeOfExtractor<>(expectedClass);
    }

    @Override
    public Optional<List<Object>> unapply(S value) {
      Optional<T> opt = typeOfExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return typeOfExtractor.getExtractorClass();
    }
  }

  public static <S, T> DecomposableMatchBuilder1<S, T> typeOf(Class<T> clazz) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any()); // The extractor takes care of the matching

    return new DecomposableMatchBuilder1<>(matchers, 0, new TypeOfFieldExtractor<>(clazz));
  }
}
