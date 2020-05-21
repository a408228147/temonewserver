package com.creams.temo.annotation;

import java.lang.reflect.InvocationTargetException;

public class ReflectTest {
    @Reflect
    public static void say1(String name){
        System.out.println(name);
    }
    @Reflect(name = "zyk")
    public static  void  say2(String name){
        System.out.println(name);
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        ReflectProcessor reflectProcessor =new ReflectProcessor();
        reflectProcessor.praseMethod(ReflectTest.class);
    }
}
