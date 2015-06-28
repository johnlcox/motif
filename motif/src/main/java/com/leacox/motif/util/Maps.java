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

import java.util.TreeMap;

/**
 * A utility class for quickly building maps for internal motif and extension usage.
 *
 * <p>Caution: This class is not meant to be used outside of internal motif and motif extensions.
 * In general {@code Guava} should be used instead of this.
 *
 * @author John Leacox
 */
public final class Maps {
  private Maps() {
  }

  public static <K, V> TreeMap<K, V> treeMapOf(K k1, V v1) {
    TreeMap<K, V> treeMap = new TreeMap<>();
    treeMap.put(k1, v1);
    return treeMap;
  }

  public static <K, V> TreeMap<K, V> treeMapOf(K k1, V v1, K k2, V v2) {
    TreeMap<K, V> treeMap = new TreeMap<>();
    treeMap.put(k1, v1);
    treeMap.put(k2, v2);
    return treeMap;
  }

  public static <K, V> TreeMap<K, V> treeMapOf(K k1, V v1, K k2, V v2, K k3, V v3) {
    TreeMap<K, V> treeMap = new TreeMap<>();
    treeMap.put(k1, v1);
    treeMap.put(k2, v2);
    treeMap.put(k3, v3);
    return treeMap;
  }
}
