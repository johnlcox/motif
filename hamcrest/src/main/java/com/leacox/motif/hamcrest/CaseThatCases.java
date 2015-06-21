package com.leacox.motif.hamcrest;

import static org.hamcrest.CoreMatchers.equalTo;

import com.leacox.motif.decomposition.DecomposableMatchBuilder1;
import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.extractor.FieldExtractor;
import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.util.Lists;

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

  private static class HamcrestMatcher<T> implements Matcher<T> {
    private final org.hamcrest.Matcher<T> matcher;

    private HamcrestMatcher(org.hamcrest.Matcher<T> matcher) {
      this.matcher = matcher;
    }

    static <T> HamcrestMatcher<T> of(org.hamcrest.Matcher<T> matcher) {
      return new HamcrestMatcher<>(matcher);
    }

    @Override
    public boolean matches(Object arg) {
      return matcher.matches(arg);
    }
  }

  public static <T> DecomposableMatchBuilder1<T, T> caseThat(org.hamcrest.Matcher<T> matcher) {
    @SuppressWarnings("unchecked")
    List<Matcher<Object>> matchers = Lists.of((Matcher<Object>) HamcrestMatcher.of(matcher));

    return new DecomposableMatchBuilder1<>(matchers, 0, new IdentityFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<T, T> caseEq(T o) {
    return caseThat(equalTo(o));
  }
}
