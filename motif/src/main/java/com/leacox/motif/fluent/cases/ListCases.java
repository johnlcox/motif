package com.leacox.motif.fluent.cases;

import com.leacox.motif.fluent.MatchingExtractor0;
import com.leacox.motif.fluent.MatchingExtractor1;
import com.leacox.motif.fluent.MatchingExtractor2;
import com.leacox.motif.fluent.extractor.Extractor0;
import com.leacox.motif.fluent.extractor.Extractor1;
import com.leacox.motif.fluent.extractor.Extractor2;
import com.leacox.motif.tuple.Tuple2;

import org.hamcrest.Matcher;

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
    @Override
    public List<A> apply(A head) {
      List<A> list = new ArrayList<>();
      list.add(head);
      return list;
    }

    @Override
    public Optional<A> unapply(List<A> list) {
      if (list.size() == 1) {
        return Optional.of(list.get(0));
      }

      return Optional.empty();
    }
  }

  private static class ListExtractor<A> implements Extractor2<List<A>, A, List<A>> {
    @Override
    public List<A> apply(A head, List<A> tail) {
      List<A> list = new ArrayList<>();
      list.add(head);
      list.addAll(tail);
      return list;
    }

    @Override
    public Optional<Tuple2<A, List<A>>> unapply(List<A> list) {
      if (list.isEmpty()) {
        return Optional.empty();
      }

      return Optional.of(Tuple2.of(list.get(0), list.subList(1, list.size())));
    }
  }

  private static class EmptyListExtractor<A> implements Extractor0<List<A>> {
    @Override
    public List<A> apply() {
      return Collections.emptyList();
    }

    @Override
    public boolean unapply(List<A> list) {
      return list.isEmpty();
    }
  }

  public static <T> MatchingExtractor0<List<T>> caseNil() {
    return new MatchingExtractor0<>(new EmptyListExtractor<>());
  }

  public static <T> MatchingExtractor1<List<T>, T> caseHeadNil(Matcher<T> head) {
    return new MatchingExtractor1<>(new HeadExtractor<>(), head);
  }

  public static <T> MatchingExtractor2<List<T>, T, List<T>> caseHeadTail(
      Matcher<T> head, Matcher<List<T>> tail) {
    return new MatchingExtractor2<>(new ListExtractor<>(), head, tail);
  }
}
