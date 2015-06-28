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
package com.leacox.motif.util;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class for quickly building lists for internal motif and extension usage.
 *
 * <p>Caution: This class is not meant to be used outside of internal motif and motif extensions.
 * In general {@code Guava} should be used instead of this.
 *
 * @author John Leacox
 */
public final class Lists {
  private Lists() {
  }

  public static <E> List<E> of(E e1) {
    List<E> list = new ArrayList<>();
    list.add(e1);
    return list;
  }

  public static <E> List<E> of(E e1, E e2) {
    List<E> list = new ArrayList<>();
    list.add(e1);
    list.add(e2);
    return list;
  }

  public static <E> List<E> of(E e1, E e2, E e3) {
    List<E> list = new ArrayList<>();
    list.add(e1);
    list.add(e2);
    list.add(e3);
    return list;
  }

  public static <E> List<E> of(E e1, E e2, E e3, E e4) {
    List<E> list = new ArrayList<>();
    list.add(e1);
    list.add(e2);
    list.add(e3);
    list.add(e4);
    return list;
  }
}
