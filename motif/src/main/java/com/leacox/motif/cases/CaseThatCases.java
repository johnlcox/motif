package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.decomposition.DecomposableMatchBuilder1;
import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.extractor.FieldExtractor;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;
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

  private static class IdentityFieldExtractor<T> implements FieldExtractor<T> {
    private final IdentityExtractor<T> identityExtractor = new IdentityExtractor<>();

    @Override
    public Optional<List<Object>> unapply(T value) {
      Optional<T> opt = identityExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return identityExtractor.getExtractorClass();
    }
  }

  public static <T> DecomposableMatchBuilder1<T, T> caseThat(Matcher<T> matcher) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add((Matcher<Object>) matcher);

    return new DecomposableMatchBuilder1<>(matchers, 0, new IdentityFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<T, T> caseEq(T o) {
    return caseThat(eq(o));
  }
}
