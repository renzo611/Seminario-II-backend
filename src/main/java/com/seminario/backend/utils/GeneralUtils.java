package com.seminario.backend.utils;

import java.util.Collection;

public class GeneralUtils {
    public static<T> boolean isValidCollection(Collection<T> collection) {
        return collection != null && !collection.isEmpty();
    }
}
