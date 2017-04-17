package com.ucf.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/*
* ListUtils is a useful collection of short functions that can be used to rebuld
* a list of objects in a different type.
* */

public class ListUtils {

  public static <T, E> List<E> transform(List<T> from, Function<T, E> func) {
    if (from == null)
      return null;

    return from.stream().map(func).collect(Collectors.toList());
  }

  public static <T, E> List<E> parallelTransform(List<T> from, Function<T, E> func) {
    if (from == null)
      return null;

    return from.parallelStream().map(func).collect(Collectors.toList());
  }

  public static <T> List<T> filter(List<T> from, Predicate<? super T> func) {
    if (from == null)
      return null;

    return from.stream().filter(func).collect(Collectors.toList());
  }

  public static <T> List<T> parallelFilter(List<T> from, Predicate<? super T> func) {
    if (from == null)
      return null;

    return from.parallelStream().filter(func).collect(Collectors.toList());
  }

  public static <T> Boolean contains(List<T> from, Predicate<? super T> func) {
    if (from == null)
      return false;

    return from.stream().anyMatch(func);
  }

  public static <T> Boolean parallelContains(List<T> from, Predicate<? super T> func) {
    if (from == null)
      return false;

    return from.parallelStream().anyMatch(func);
  }

  public static <T> T select(List<T> from, Predicate<? super T> func) throws RuntimeException {
    List<T> search = from.stream().filter(func).collect(Collectors.toList());

    if (search.size() > 1)
      throw new RuntimeException("Collection contains more than one item");
    else if (search.size() != 1)
      return null;

    return search.get(0);
  }

  public static <T> Collector<T, List<T>, T> singletonCollector() {
    return Collector.of(
      ArrayList::new,
      List::add,
      (left, right) -> {
        left.addAll(right);
        return left;
      },
      list -> {
        if (list.size() != 1) {
          throw new IllegalStateException();
        }
        return list.get(0);
      }
    );
  }
}
