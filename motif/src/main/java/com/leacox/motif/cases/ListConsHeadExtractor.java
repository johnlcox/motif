/*
 * Copyright (C) 2015 John Leacox
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.leacox.motif.cases;

import com.leacox.motif.extract.Extractor1;

import java.util.List;
import java.util.Optional;

/**
 * An extractor for extracting a {@link List} with a single element.
 *
 * @author John Leacox
 */
public class ListConsHeadExtractor<A> implements Extractor1<List<A>, A> {
  private ListConsHeadExtractor() {
  }

  /**
   * Creates a new instance of {@link ListConsHeadExtractor}.
   */
  public static <A> ListConsHeadExtractor<A> create() {
    return new ListConsHeadExtractor<>();
  }

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
