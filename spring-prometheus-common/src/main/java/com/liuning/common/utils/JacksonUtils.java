package com.liuning.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuning.common.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author: liuning
 * @description: Jackson工具类
 * @create: 2020-07-19 00:04
 * @version: 1.0
 */
public class JacksonUtils {

    private static final ObjectMapper mapper;
    private static final Logger log = LoggerFactory.getLogger(JacksonUtils.class);

    static {
         mapper = new ObjectMapper();
        // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        // 允许key没有双引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许key有单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 允许整数以0开头
        mapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);
        // 允许字符串中存在回车换行控制符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

    public static String toJSONString(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Jackson serialize error", e);
            throw new AppException("999999", "JSON序列化异常");
        }
    }

    public static <T> T parseObject(String jsonStr, Class<T> tClass) {
        try {
            return mapper.readValue(jsonStr, tClass);
        } catch (IOException e) {
            log.error("Jackson deserialize error", e);
            throw new AppException("999999", "JSON反序列化异常");
        }
    }
}
