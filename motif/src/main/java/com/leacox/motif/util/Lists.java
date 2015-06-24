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
