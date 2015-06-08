package com.leacox.motif.cases;

import com.leacox.motif.extractor.Extractor0;
import com.leacox.motif.extractor.Extractor1;
import com.leacox.motif.extractor.Extractor2;
import com.leacox.motif.matching.MatchingExtractor0;
import com.leacox.motif.matching.MatchingExtractor1;
import com.leacox.motif.matching.MatchingExtractor2;
import com.leacox.motif.tuple.Tuple2;

import org.hamcrest.Matcher;

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

  public static <T> MatchingExtractor0<List<T>> caseNil() {
    return MatchingExtractor0.create(new EmptyListExtractor<>());
  }

  public static <T> MatchingExtractor1<List<T>, T> caseHeadNil(Matcher<T> head) {
    return MatchingExtractor1.create(new HeadExtractor<>(), head);
  }

  public static <T> MatchingExtractor2<List<T>, T, List<T>> caseHeadTail(
      Matcher<T> head, Matcher<List<T>> tail) {
    return MatchingExtractor2.create(new ListExtractor<>(), head, tail);
  }
}
