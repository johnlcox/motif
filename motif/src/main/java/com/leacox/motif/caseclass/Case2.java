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
package com.leacox.motif.caseclass;

import com.leacox.motif.tuple.Tuple2;

/**
 * Interface for a case class of 2 elements.
 *
 * <p>Case classes must be able to extract their elements into a {@code Tuple}.
 *
 * @author John Leacox
 */
public interface Case2<A, B> {
  /**
   * Extracts the case class fields into a {@link Tuple2}.
   */
  Tuple2<A, B> extract();
}
