package com.leacox.motif.cases;

import com.leacox.motif.extraction.Extractor2;
import com.leacox.motif.tuple.Tuple2;

import java.util.List;
import java.util.Optional;

/**
 * @author John Leacox
 */
public class ListExtractor<A> implements Extractor2<List<A>, A, List<A>> {
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
