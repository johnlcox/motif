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
import com.leacox.motif.tuple.Tuple1;

import java.util.Optional;

/**
 * @author John Leacox
 */
public class Tuple1Extractor<A> implements Extractor1<Tuple1<A>, A> {
  @Override
  public Optional<A> unapply(Tuple1<A> tuple1) {
    return tuple1.first() == null ? Optional.empty() : Optional.ofNullable(tuple1.first());
  }

  @Override
  public Class<Tuple1> getExtractorClass() {
    return Tuple1.class;
  }
}
