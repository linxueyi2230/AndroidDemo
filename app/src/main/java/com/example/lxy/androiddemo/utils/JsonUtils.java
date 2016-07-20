package com.example.lxy.androiddemo.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    private static JsonUtils instance;

    private JsonUtils() {
    }

    public static JsonUtils getInstance() {
        if (instance == null) {
            instance = new JsonUtils();
        }
        return instance;
    }

    private static Gson gson = new GsonBuilder().setDateFormat(
            "yyyy-MM-dd HH:mm:ss").create();

    public static Gson getGson() {
        return gson;
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }
}

