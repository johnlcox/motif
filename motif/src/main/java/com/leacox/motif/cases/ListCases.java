package com.leacox.motif.cases;

import static com.leacox.motif.matchers.ArgumentMatchers.any;
import static com.leacox.motif.matchers.ArgumentMatchers.eq;

import com.leacox.motif.MatchesAny;
import com.leacox.motif.extraction.DecomposableMatchBuilder0;
import com.leacox.motif.extraction.DecomposableMatchBuilder1;
import com.leacox.motif.extraction.DecomposableMatchBuilder2;
import com.leacox.motif.extraction.Extractor0;
import com.leacox.motif.extraction.Extractor1;
import com.leacox.motif.extraction.Extractor2;
import com.leacox.motif.extraction.FieldExtractor;
import com.leacox.motif.matchers.Matcher;
import com.leacox.motif.tuple.Tuple2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public final class ListCases {
  private ListCases() {
  }

  private static class HeadExtractor<A> implements Extractor1<List<A>, A> {
    //@Override
    //public List<A> apply(A head) {
    //  List<A> list = new ArrayList<>();
    //  list.add(head);
    //  return list;
    //}

    @Override
    public Optional<A> unapply(List<A> list) {
      if (list.size() == 1) {
        return Optional.of(list.get(0));
      }

      return Optional.empty();
    }

    @Override
    public Class<List> getExtractorClass() {
      return List.class;
    }
  }

  private static class HeadFieldExtractor<A> implements FieldExtractor<List<A>> {
    private final HeadExtractor<A> headExtractor = new HeadExtractor<>();

    @Override
    public Optional<List<Object>> unapply(List<A> value) {
      Optional<A> opt = headExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return headExtractor.getExtractorClass();
    }
  }

  private static class ListExtractor<A> implements Extractor2<List<A>, A, List<A>> {
    //@Override
    //public List<A> apply(A head, List<A> tail) {
    //  List<A> list = new ArrayList<>();
    //  list.add(head);
    //  list.addAll(tail);
    //  return list;
    //}

    @Override
    public Optional<Tuple2<A, List<A>>> unapply(List<A> list) {
      if (list.isEmpty()) {
        return Optional.empty();
      }

      return Optional.of(Tuple2.of(list.get(0), list.subList(1, list.size())));
    }

    @Override
    public Class<List> getExtractorClass() {
      return List.class;
    }
  }

  private static class HeadTailFieldExtractor<A> implements FieldExtractor<List<A>> {
    private final ListExtractor<A> listExtractor = new ListExtractor<>();

    @Override
    public Optional<List<Object>> unapply(List<A> value) {
      Optional<Tuple2<A, List<A>>> opt = listExtractor.unapply(value);
      if (!opt.isPresent()) {
        return Optional.empty();
      }

      List<Object> fields = new ArrayList<>();
      fields.add(opt.get().first());
      fields.add(opt.get().second());

      return Optional.of(fields);
    }

    @Override
    public Class<?> getExtractorClass() {
      return listExtractor.getExtractorClass();
    }
  }

  private static class EmptyListExtractor<A> implements Extractor0<List<A>> {
    //@Override
    //public List<A> apply() {
    //  return Collections.emptyList();
    //}

    @Override
    public boolean unapply(List<A> list) {
      return list.isEmpty();
    }

    @Override
    public Class<List> getExtractorClass() {
      return List.class;
    }
  }

  private static class NilFieldExtractor<A> implements FieldExtractor<List<A>> {
    private final EmptyListExtractor<A> emptyListExtractor = new EmptyListExtractor<>();

    @Override
    public Optional<List<Object>> unapply(List<A> value) {
      if (!emptyListExtractor.unapply(value)) {
        return Optional.empty();
      }

      return Optional.of(Collections.emptyList());
    }

    @Override
    public Class<?> getExtractorClass() {
      return emptyListExtractor.getExtractorClass();
    }
  }

  public static <T> DecomposableMatchBuilder0<List<T>> nil() {
    return new DecomposableMatchBuilder0<>(Collections.emptyList(), new NilFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headNil(T head) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(head));

    return new DecomposableMatchBuilder1<>(matchers, 0, new HeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder1<List<T>, T> headNil(MatchesAny head) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());

    return new DecomposableMatchBuilder1<>(matchers, 0, new HeadFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder2<List<T>, T, List<T>> headTail(
      T head, MatchesAny tail) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(eq(head));
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new HeadTailFieldExtractor<>());
  }

  public static <T> DecomposableMatchBuilder2<List<T>, T, List<T>> headTail(
      MatchesAny head, MatchesAny tail) {
    List<Matcher<Object>> matchers = new ArrayList<>();
    matchers.add(any());
    matchers.add(any());

    return new DecomposableMatchBuilder2<>(
        matchers, Tuple2.of(0, 1), new HeadTailFieldExtractor<>());
  }
}
