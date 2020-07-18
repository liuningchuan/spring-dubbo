package com.liuning.common.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;

/**
 * @author: liuning
 * @description: gson工具类
 * @create: 2020-07-14 00:29
 * @version: 1.0
 */
public class JsonUtils {

    private static final Gson gson = new Gson();
    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

    /**
     * Json转对象
     *
     * @param jsonStr Json字符串
     * @param clazz   对象
     * @return 对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> clazz) {
        try {
            return gson.fromJson(jsonStr, clazz);
        } catch (JsonSyntaxException e) {
            log.error("GSON.fromJson error", e);
        }
        return null;
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
        try {
            return gson.fromJson(jsonStr, typeToken.getType());
        } catch (JsonSyntaxException e) {
            log.error("GSON.fromJson error", e);
        }
        return null;
    }
}
