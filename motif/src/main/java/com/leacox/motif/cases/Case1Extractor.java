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

import com.leacox.motif.caseclass.Case1;
import com.leacox.motif.extract.Extractor1;

import java.util.Optional;

/**
 * An extract for extracting the fields of a {@link Case1}.
 *
 * @author John Leacox
 */
public class Case1Extractor<T extends Case1<A>, A> implements Extractor1<T, A> {
  private final Class<T> caseClazz;

  private Case1Extractor(Class<T> caseClazz) {
    this.caseClazz = caseClazz;
  }

  /**
   * Creates a new instances of {@link Case1Extractor} for the specified {@link Case1} class.
   */
  public static <T extends Case1<A>, A> Case1Extractor<T, A> create(Class<T> caseClazz) {
    return new Case1Extractor<>(caseClazz);
  }

  @Override
  public Optional<A> unapply(T t) {
    return caseClazz.isAssignableFrom(t.getClass()) ? Optional.of(t.extract().first())
        : Optional.empty();
  }

  @Override
  public Class getExtractorClass() {
    return caseClazz;
  }
}
