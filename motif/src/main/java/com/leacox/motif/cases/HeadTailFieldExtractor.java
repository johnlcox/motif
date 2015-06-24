package com.leacox.motif.cases;

/**
 * @author John Leacox
 */

import com.leacox.motif.extraction.FieldExtractor;
import com.leacox.motif.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HeadTailFieldExtractor<A> implements FieldExtractor<List<A>> {
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
