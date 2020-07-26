package com.liuning.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.*;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.reflect.TypeToken;
import com.liuning.common.utils.JsonUtils;
import com.liuning.dao.entity.User;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.ParsePosition;
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

    @Test
    public void gsonTest() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, (JsonSerializer<Date>) (date, type, jsonSerializationContext) ->
                        new JsonPrimitive(FastDateFormat.getInstance("yyyyMMdd").format(date))
                )
                .registerTypeAdapter(Date.class, (JsonDeserializer<Date>) (jsonElement, type, jsonDeserializationContext) -> {
                    JsonPrimitive jp = jsonElement.getAsJsonPrimitive();
                    if (jp.isNumber()) {
                        return new Date(jp.getAsLong());
                    } else {
                        String date = jp.getAsString();
                        try {
                            FastDateFormat.getInstance("yyyyMMdd").format(date);
                        } catch (Exception ignore) {
                        }
                        try {
                            return ISO8601Utils.parse(date, new ParsePosition(0));
                        } catch (ParseException e) {
                            throw new JsonSyntaxException(date, e);
                        }
                    }
                })
                .create();

        DateExample date = new DateExample();
        date.setName("liuning");
        date.setDescription("刘宁");
        date.setDate(new Date());
        System.out.println(gson.toJson(date));

        String dateString = "{\"name\":\"liuning\",\"description\":\"刘宁\",\"date\":\"20200727\"}";
        DateExample dateExample = gson.fromJson(dateString, DateExample.class);
        System.out.println("date is " + dateExample.getDate());

        //第二种处理Date序列化的方式
        Gson GSON = new GsonBuilder().setDateFormat("yyyyMMddhhmmss").create();
        System.out.println("===========");
        System.out.println(GSON.toJson(date));

        String dateString2 = "{\"name\":\"liuning\",\"description\":\"刘宁\",\"date\":\"20200727224140\"}";
        DateExample dateExample2 = GSON.fromJson(dateString2, DateExample.class);
        System.out.println("date is " + dateExample2.getDate());

    }

    private static class DateExample {

        public String name;

        public String description;

        public Date date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
