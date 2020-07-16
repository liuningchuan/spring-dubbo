package com.liuning.test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liuning.common.utils.JsonUtils;
import com.liuning.dto.Result;
import com.liuning.web.StartApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;

/**
 * @author: liuning
 * @description: json测试类
 * @create: 2020-07-16 23:53
 * @version: 1.0
 */
public class JSONTest {

    public static void main(String[] args) {
        String json = "{\"data\":\"data from server\"}";
        Type type = new TypeToken<Result<String>>(){}.getType();
        Result<String> result = JsonUtils.fromJson(json, type);
        System.out.print(result);
    }
}
