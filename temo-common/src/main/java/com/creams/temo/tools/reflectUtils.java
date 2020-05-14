package com.creams.temo.tools;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 反射工具类
 */
public class reflectUtils<T> {
    // A已经是一个泛型类，其类型参数是T
    public static <T> List<T> JobListtoBean(T f, List<JSONObject> list) {
        // 再在其中定义一个泛型方法，该方法的类型参数也是T
        return list.stream().map(t -> {
            Field[] fields = new Field[0];
            try {
                fields = f.getClass().newInstance().getClass().getDeclaredFields();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Arrays.stream(fields).forEach(i -> {
                try {
                    i.setAccessible(true);
                    i.set(f, t.get(i.getName()));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return f;
        }).collect(Collectors.toList());

    }

    public static <T> T JobtoBean(T f, JSONObject jsonObject) {
        // 再在其中定义一个泛型方法，该方法的类型参数也是T
            Field[] fields = new Field[0];
            try {
                fields = f.getClass().newInstance().getClass().getDeclaredFields();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Arrays.stream(fields).forEach(i -> {
                try {
                    i.setAccessible(true);
                    i.set(f, jsonObject.get(i.getName()));

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return f;
    }
    public static void main(String[] args) {
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("id",1);
        jsonObject.put("name",2);
        Bean bean = new Bean();
        bean= JobtoBean(bean,jsonObject);

        List<JSONObject> list= Lists.newArrayList();
        list.add(jsonObject);
        List<Bean> beans = JobListtoBean(bean,list);
    }
}

class Bean{
    private int id;



    private int name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
