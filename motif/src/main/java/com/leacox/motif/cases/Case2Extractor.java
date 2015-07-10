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

import com.leacox.motif.caseclass.Case2;
import com.leacox.motif.extract.Extractor2;
import com.leacox.motif.tuple.Tuple2;

import java.util.Optional;

/**
 * @author John Leacox
 */
public class Case2Extractor<T extends Case2<A, B>, A, B> implements Extractor2<T, A, B> {
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
