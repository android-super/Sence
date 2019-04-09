package com.sence.utils;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class MapTransformUtils {

    public static <T> T mapToObject(Map<String, Object> map, Class<T> beanClass) throws Exception {
        if (map == null)
            return null;

        T obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return map;
    }


    public static Map<String, RequestBody> objectToMapImg(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, RequestBody> map = new HashMap<>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        try {
            for (Field field : declaredFields) {
                field.setAccessible(true);
                RequestBody body = null;
                if (field.get(obj) instanceof String) {
                    body = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), (String) field.get(obj));
                } else if (field.get(obj) instanceof File) {
                    body = RequestBody.create(MediaType.parse("multipart/form-data;charset=UTF-8"), (File) field.get(obj));
                }
                map.put(field.getName(), body);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
}