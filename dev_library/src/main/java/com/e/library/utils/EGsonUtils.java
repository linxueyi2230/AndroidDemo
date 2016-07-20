package com.e.library.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lxy on 2016/5/10.
 */
public final class EGsonUtils {

    private EGsonUtils(){};

    private static Gson gson = new GsonBuilder().setDateFormat(
            "yyyy-MM-dd HH:mm:ss").create();

    public static Gson getGson() {
        return gson;
    }

    public static  <T> T toObject(String data,Class<T> clz) {
        if (TextUtils.isEmpty(data))
            return null;
        return fromJson(data, clz);
    }

    public static <T> List<T> toList(String data,Class<T> clz) {
        if (TextUtils.isEmpty(data))
            return null;
        Type listType = type(List.class, clz);
        List<T> list = fromJson(data, listType);
        return list;
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static String obj2Json(Object obj) {
        return gson.toJson(obj);
    }

    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }

        };
    }

}
