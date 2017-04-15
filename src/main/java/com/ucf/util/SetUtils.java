package com.ucf.util;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SetUtils {

    public static <T, E> Set<E> transform(Set<T> from, Function<T, E> func) {
        return from.stream().map(func).collect(Collectors.toSet());
    }

    public static <T, E> Set<E> parallelTransform(Set<T> from, Function<T, E> func) {
        return from.parallelStream().map(func).collect(Collectors.toSet());
    }

    public static <T> Set<T> filter(Set<T> from, Predicate<? super T> func) {
        return from.stream().filter(func).collect(Collectors.toSet());
    }

    public static <T> Set<T> parallelFilter(Set<T> from, Predicate<? super T> func) {
        return from.parallelStream().filter(func).collect(Collectors.toSet());
    }
}
