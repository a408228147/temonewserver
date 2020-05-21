package com.creams.temo.annotation;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.*;
public class ReflectProcessor {
    public void praseMethod(final Class<?> clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Object obj = clazz.getConstructor(new Class[]{}).newInstance();
        final Method[] methods = clazz.getDeclaredMethods();
        for (final Method method :methods){
            final Reflect reflect = method.getAnnotation(Reflect.class);
            if (reflect!=null){
                method.invoke(obj,reflect.name());
            }
        }
    }
}
