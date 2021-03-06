package com.ucf.util;

import java.util.Optional;
import java.util.function.Function;

public class OptionalUtils {

    public static <T, E> E unwrap(T from, Function<T, E> func) {
        return Optional.ofNullable(from).map(func).orElse(null);
    }

}
