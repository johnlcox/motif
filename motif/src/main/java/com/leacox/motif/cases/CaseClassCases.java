package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.decomposition.DecomposableMatchBuilder0;
import com.leacox.motif.decomposition.DecomposableMatchBuilder1;
import com.leacox.motif.decomposition.DecomposableMatchBuilder2;
import com.leacox.motif.decomposition.MatchesAny;
import com.leacox.motif.extractor.Extractor2;
import com.leacox.motif.extractor.Extractor3;
import com.leacox.motif.extractor.FieldExtractor;
import com.leacox.motif.matching.MatchingExtractor3;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import org.hamcrest.Matcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class CaseClassCases {
  private CaseClassCases() {
  }

  private static class Case2Extractor<T extends Case2<A, B>, A, B> implements Extractor2<T, A, B> {
    private final Class<T> caseClazz;

    Case2Extractor(Class<T> caseClazz) {
      this.caseClazz = caseClazz;
    }

    @Override
    public Optional<Tuple2<A, B>> unapply(T t) {
      return caseClazz.isAssignableFrom(t.getClass()) ? Optional.of(t.extract()) : Optional.empty();
    }

    @Override
    public Class getExtractorClass() {
      return caseClazz;
    }
  }

  private static class Case2FieldExtractor<T extends Case2<A, B>, A, B>
      implements FieldExtractor<T> {
    private final Case2Extractor<T, A, B> case2Extractor;

    Case2FieldExtractor(Class<T> caseClazz) {
      this.case2Extractor = new Case2Extractor<>(caseClazz);
    }

    @Override
    public Optional<List<Object>> unapply(T value) {
      Optional<Tuple2<A, B>> opt = case2Extractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      return Optional.of(opt.get().toList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return case2Extractor.getExtractorClass();
    }
  }

  private static class Case3Extractor<T extends Case3<A, B, C>, A, B, C>
      implements Extractor3<T, A, B, C> {
    private final Class<T> caseClazz;

    Case3Extractor(Class<T> caseClazz) {
      this.caseClazz = caseClazz;
    }

    //@Override
    //public Case3<A, B, C> apply(A a, B b, C c) {
    //  caseClazz.getConstructor(a.getClass(), b.getClass(), c.getClass());
    //
    //  return null;
    //}

    @Override
    public Optional<Tuple3<A, B, C>> unapply(T t) {
      if (!caseClazz.isAssignableFrom(t.getClass())) {
        return Optional.empty();
      }

      return Optional.of(t.extract());
    }

    @Override
    public Class getExtractorClass() {
      return caseClazz;
    }
  }

  //public static <T extends Case2<A, B>, A, B> MatchingExtractor2<T, A, B> case2(
  //    Class<T> clazz, Matcher<A> a, Matcher<B> b) {
  //  return MatchingExtractor2.create(new Case2Extractor<>(clazz), a, b);
  //}

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder0<T> case2(
      Class<T> clazz, A a, B b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(a));
    matchers.add(eq(b));

    return new DecomposableMatchBuilder0<>(matchers, new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder1<T, A> case2(
      Class<T> clazz, MatchesAny a, B b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());
    matchers.add(eq(b));

    return new DecomposableMatchBuilder1<>(matchers, 0, new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder1<T, B> case2(
      Class<T> clazz, A a, MatchesAny b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(a));
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 1, new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case2<A, B>, A, B> DecomposableMatchBuilder2<T, A, B> case2(
      Class<T> clazz, MatchesAny a, MatchesAny b) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new Case2FieldExtractor<>(clazz));
  }

  public static <T extends Case3<A, B, C>, A, B, C> MatchingExtractor3<T, A, B, C> case3(
      Class<T> clazz, Matcher<A> a, Matcher<B> b, Matcher<C> c) {
    return MatchingExtractor3.create(new Case3Extractor<>(clazz), a, b, c);
  }
}
