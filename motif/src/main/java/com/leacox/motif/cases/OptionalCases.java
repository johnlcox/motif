package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.decomposition.DecomposableMatchBuilder0;
import com.leacox.motif.decomposition.DecomposableMatchBuilder1;
import com.leacox.motif.decomposition.DecomposableMatchBuilder2;
import com.leacox.motif.decomposition.MatchesAny;
import com.leacox.motif.extractor.Extractor0;
import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.extractor.FieldExtractor;

import com.leacox.motif.matchers.Matcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class OptionalCases {
  private OptionalCases() {
  }

  private static class OptionalExtractor<T> implements Extractor1<Optional<T>, T> {
    //@Override
    //public Optional<T> apply(T t) {
    //  return Optional.ofNullable(t);
    //}

    @Override
    public Optional<T> unapply(Optional<T> t) {
      if (t.isPresent()) {
        return t;
      } else {
        return Optional.empty();
      }
    }

    @Override
    public Class getExtractorClass() {
      return Optional.class;
    }
  }

  private static class NoneExtractor<T> implements Extractor0<Optional<T>> {
    @Override
    public boolean unapply(Optional<T> t) {
      return !t.isPresent();
    }

    @Override
    public Class<Optional> getExtractorClass() {
      return Optional.class;
    }
  }

  private static class OptionalFieldExtractor<T> implements FieldExtractor<Optional<T>> {
    OptionalExtractor<T> optionalExtractor = new OptionalExtractor<>();

    @Override
    public Optional<List<Object>> unapply(Optional<T> value) {
      Optional<T> opt = optionalExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return optionalExtractor.getExtractorClass();
    }
  }

  private static class NoneFieldExtractor<T> implements FieldExtractor<Optional<T>> {
    NoneExtractor<T> noneExtractor = new NoneExtractor<>();

    @Override
    public Optional<List<Object>> unapply(Optional<T> value) {
      if (!noneExtractor.unapply(value)) {
        return Optional.empty();
      }

      return Optional.of(Collections.emptyList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return noneExtractor.getExtractorClass();
    }
  }

  public static <T> DecomposableMatchBuilder0<Optional<T>> some(T a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(a));

    return new DecomposableMatchBuilder0<>(matchers, new OptionalFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<Optional<T>, T> some(MatchesAny a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 0, new OptionalFieldExtractor<>());
  }

  public static <T, A> DecomposableMatchBuilder1<Optional<T>, A> some(
      DecomposableMatchBuilder1<T, A> a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<Optional<T>, T>(
        matchers, 0, new OptionalFieldExtractor<>()).decomposeFirst(a);
  }

  public static <T, A, B> DecomposableMatchBuilder2<Optional<T>, A, B> some(
      DecomposableMatchBuilder2<T, A, B> a) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<Optional<T>, T>(
        matchers, 0, new OptionalFieldExtractor<>()).decomposeFirst(a);
  }

  //public static <T, A, B> MatchingExtractor2<Optional<T>, A, B> some(
  //    MatchingExtractor2<T, A, B> nestedExtractor) {
  //
  //  Extractor2<Optional<T>, A, B> extractor1 =
  //      new OptionalExtractor<T>().decomposeFirst(nestedExtractor.extractor);
  //
  //  return MatchingExtractor2
  //      .create(extractor1, nestedExtractor.toMatchA, nestedExtractor.toMatchB);
  //}

  //public static <T> MatchingExtractor0<Optional<T>> caseNone() {
  //  Extractor0<Optional<T>> extractor = new Extractor0<Optional<T>>() {
  //    //@Override
  //    //public Optional<T> apply() {
  //    //  return Optional.empty();
  //    //}
  //
  //    @Override
  //    public boolean unapply(Optional<T> t) {
  //      return !t.isPresent();
  //    }
  //
  //    @Override
  //    public Class<Optional> getExtractorClass() {
  //      return Optional.class;
  //    }
  //  };
  //
  //  return MatchingExtractor0.create(extractor);
  //}

  public static <T> DecomposableMatchBuilder0<Optional<T>> none() {
    return new DecomposableMatchBuilder0<>(Collections.emptyList(), new NoneFieldExtractor<>());
  }
}
