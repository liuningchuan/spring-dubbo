package test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.reflect.TypeToken;
import com.liuning.common.utils.JacksonUtils;
import com.liuning.common.utils.JsonUtils;
import com.liuning.dao.entity.User;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
    public void jacksonUtilsTest() {
        String jsonStr = "[{\"email\":\"email0\",\"id\":0,\"name\":\"user0\",\"password\":\"password0\"}," +
                "{\"email\":\"email1\",\"id\":1,\"name\":\"user1\",\"password\":\"password1\"}," +
                "{\"email\":\"email2\",\"id\":2,\"name\":\"user2\",\"password\":\"password2\"}," +
                "{\"email\":\"email3\",\"id\":3,\"name\":\"user3\",\"password\":\"password3\"}]";
        List<User> userList = JacksonUtils.parseObject(jsonStr, new TypeReference<List<User>>(){});
        System.out.println(userList.size());

        String userStr = "{\"email\":\"599522516@qq.com\",\"id\":1,\"name\":\"liuning\",\"password\":\"open123\"}";
        User user = JacksonUtils.parseObject(userStr, User.class);
        System.out.println(user);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("name", "LiuNing");
        map.put("age", "25");
        map.put("sex", "man");
        System.out.println(JacksonUtils.toJSONString(map));
    }
}
