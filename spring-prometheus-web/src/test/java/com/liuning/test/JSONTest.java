package com.liuning.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.liuning.common.utils.JsonUtils;
import com.liuning.dao.entity.User;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.*;

/**
 * @author: liuning
 * @description: json测试类
 * @create: 2020-07-16 23:53
 * @version: 1.0
 */
public class JSONTest {

    @Test
    public void jsonUtilsTest() {
        String jsonStr = "[{\"email\":\"email0\",\"id\":0,\"name\":\"user0\",\"password\":\"password0\"}," +
                "{\"email\":\"email1\",\"id\":1,\"name\":\"user1\",\"password\":\"password1\"}," +
                "{\"email\":\"email2\",\"id\":2,\"name\":\"user2\",\"password\":\"password2\"}," +
                "{\"email\":\"email3\",\"id\":3,\"name\":\"user3\",\"password\":\"password3\"}]";
        System.out.println(jsonStr);
        Type type = new TypeToken<List<User>>(){}.getType();
        List<User> userList = JsonUtils.fromJson(jsonStr, type);

        for (User user : userList) {
            System.out.println(JsonUtils.toJson(user));
        }

        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", "LiuNing");
        map.put("age", "25");
        map.put("sex", "man");
        System.out.println(JsonUtils.toJson(map));
    }

    @Test
    public void fastJsonTest() {
        List<User> list = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            User user = new User();
            user.setId(i);
            user.setName("user" + i);
            user.setEmail("email" + i);
            user.setPassword("password" + i);
            list.add(user);
        }
        String jsonStr = JSONObject.toJSONString(list);
        System.out.println(JSON.toJSONString(list));

        System.out.println("TypeReference Test begin.");

        Type type = new TypeReference<List<User>>() {}.getType();
        List<User> userList = JSON.parseObject(jsonStr, type);
        for (User user : userList) {
            System.out.println(user);
        }

        System.out.println("JSONArray Test begin.");

        JSONArray jsonArray = JSON.parseArray(jsonStr);
        System.out.println(jsonArray);
    }
}
