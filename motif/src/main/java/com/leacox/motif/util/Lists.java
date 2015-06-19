package com.leacox.motif.util;

import java.util.ArrayList;
import java.util.List;

/**
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
}
