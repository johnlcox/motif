package com.leacox.motif.cases;

import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.extractor.Extractor2;
import com.leacox.motif.extractor.Extractor3;
import com.leacox.motif.matching.MatchingExtractor2;
import com.leacox.motif.matching.MatchingExtractor3;
import com.leacox.motif.tuple.Tuple2;
import com.leacox.motif.tuple.Tuple3;

import org.hamcrest.Matcher;

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

  public static <T extends Case2<A, B>, A, B> MatchingExtractor2<T, A, B> case2(
      Class<T> clazz, Matcher<A> a, Matcher<B> b) {
    return MatchingExtractor2.create(new Case2Extractor<>(clazz), a, b);
  }

  public static <T extends Case3<A, B, C>, A, B, C> MatchingExtractor3<T, A, B, C> case3(
      Class<T> clazz, Matcher<A> a, Matcher<B> b, Matcher<C> c) {
    return MatchingExtractor3.create(new Case3Extractor<>(clazz), a, b, c);
  }
}
