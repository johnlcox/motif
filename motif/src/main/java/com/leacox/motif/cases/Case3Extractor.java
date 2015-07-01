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

import com.leacox.motif.caseclass.Case3;
import com.leacox.motif.extract.Extractor3;
import com.leacox.motif.tuple.Tuple3;

import java.util.Optional;

/**
 * @author John Leacox
 */
public class  Case3Extractor<T extends Case3<A, B, C>, A, B, C>
    implements Extractor3<T, A, B, C> {
  private final Class<T> caseClazz;

  Case3Extractor(Class<T> caseClazz) {
    this.caseClazz = caseClazz;
  }

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
