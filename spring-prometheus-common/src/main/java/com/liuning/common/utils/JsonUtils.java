package com.liuning.common.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author: liuning
 * @description: gson工具类
 * @create: 2020-07-14 00:29
 * @version: 1.0
 */
public class JsonUtils {

    private static final Gson gson = new Gson();

    /**
     * serializes the specified object into its equivalent Json representation.
     *
     * @param object the object for which json representation is to be created setting for fastjson
     * @return Json representation
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * Json转对象
     *
     * @param jsonStr Json字符串
     * @param clazz   对象
     * @return 对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        return gson.fromJson(jsonStr, clazz);
    }

    /**
     * Json转对象
     *
     * @param jsonStr Json字符串
     * @param type 对象
     * @return T
     */
    public static <T> T fromJson(String jsonStr, Type type) {
        return gson.fromJson(jsonStr, type);
    }

    /**
     * Json转对象
     *
     * @param jsonStr Json字符串
     * @param typeToken   对象
     * @return 对象
     */
    public static <T> T fromJson(String jsonStr, TypeToken<T> typeToken) {
        return gson.fromJson(jsonStr, typeToken.getType());
    }
}
