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
package com.leacox.motif.matching;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author John Leacox
 */
interface ConsumablePattern<T> {
  boolean matches(T value);

  void consume(T value);

  static <T> ConsumablePattern<T> of(Function<T, Boolean> matcher, Consumer<T> consumer) {
    return new ConsumablePattern<T>() {
      @Override
      public boolean matches(T value) {
        return matcher.apply(value);
      }

      @Override
      public void consume(T value) {
        consumer.accept(value);
      }
    };
  }
}
