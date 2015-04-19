package org.basesource.vending.annotations;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public class ReflectionUtils {

    public static <T> Collection<Class<? extends T>> scanTypes(String pkg, Class<T> type) {
        return new Reflections(pkg).getSubTypesOf(type);
    }

    public static Collection<Class<?>> scanAnnotatedTypes(String pkg, Class<Annotation> annotation) {
        return new Reflections(pkg).getTypesAnnotatedWith(annotation);
    }

    public static Class genSuperClassParamType(Class t, int i) {
        return (Class) ((ParameterizedType)t.getGenericSuperclass()).getActualTypeArguments()[i];
    }

    public static Class genInterfaceParamType(Class t, Class git, int i) {
        for(Type it : t.getGenericInterfaces()) {
            ParameterizedType itp = (ParameterizedType)it;
            if(git.isAssignableFrom((Class)itp.getRawType())) {
                return (Class) itp.getActualTypeArguments()[i];
            }
        }
        return null;
    }
}
