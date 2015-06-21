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
