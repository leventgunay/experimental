package org.basesource.vending.annotations;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.Collection;

public class ReflectionUtils {

    public static <T> Collection<Class<? extends T>> scanTypes(String pkg, Class<T> type) {
        return new Reflections(pkg).getSubTypesOf(type);
    }

    public static Collection<Class<?>> scanAnnotatedTypes(String pkg, Class<Annotation> annotation) {
        return new Reflections(pkg).getTypesAnnotatedWith(annotation);
    }
}
