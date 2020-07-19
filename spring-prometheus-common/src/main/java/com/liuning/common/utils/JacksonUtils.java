package com.liuning.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuning.common.exception.AppException;

/**
 * @author: liuning
 * @description: Jackson工具类
 * @create: 2020-07-19 00:04
 * @version: 1.0
 */
public class JacksonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String toJSONString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new AppException("999999", "JSON序列化异常");
        }
    }
}
