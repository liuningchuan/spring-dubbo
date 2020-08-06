package com.liuning.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.liuning.dao.entity.User;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuning
 * @description Alibaba Fastjson test
 * @since 2020-08-06 23:57
 */
public class FastjsonTest {

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

        //TypeReference
        Type type = new TypeReference<List<User>>() {}.getType();
        List<User> userList = JSON.parseObject(jsonStr, type);
        for (User user : userList) {
            System.out.println(user);
        }

        //JSONArray
        String arrayString = "[{\"email\":\"JSONArray\",\"id\":0,\"name\":\"user0\",\"password\":\"password0\"}]";
        JSONArray jsonArray = JSON.parseArray(arrayString);
        System.out.println("size is : " + jsonArray.size() + " and content is : " + jsonArray.get(0));

        //JSONObject
        String str = "{\"email\":\"email0\",\"id\":0,\"name\":\"user0\",\"password\":\"password0\"}";
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println("email is : " + jsonObject.get("email"));
    }

}
