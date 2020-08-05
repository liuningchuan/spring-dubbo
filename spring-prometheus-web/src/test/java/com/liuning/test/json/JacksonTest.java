package com.liuning.test.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author liuning
 * @description jackson test
 * @since 2020-08-05 23:38
 */
public class JacksonTest {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();

        // 美化输出
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        // 允许序列化空的POJO类,否则会抛出异常
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 把java.util.Date, Calendar输出为数字（时间戳）
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 在遇到未知属性的时候不抛出异常
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 强制JSON 空字符串("")转换为null对象值:
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 允许没有引号的字段名（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号（非标准）
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 强制转义非ASCII字符
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
        // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
        //支持jdk8的全新时间类库
        mapper.registerModule(new JavaTimeModule())
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module());
    }

    @Test
    public void jacksonTest_1() {

        //ObjectNode
        ObjectNode node = mapper.createObjectNode();
        node.put("name", "test");
        node.put("age", 20);
        System.out.println(node);

        //ArrayNode
        ArrayNode array = mapper.createArrayNode();
        array.add(node);
        System.out.println(array);
    }

    @Test
    public void jacksonTest_2() throws IOException {

        String str = "{\"user_name\":\"liuning\",\"age\":\"25\",\"height\":\"162\",\"money\":\"100000\",\"date\":\"2020-04-19 23-45-20\"}";
        Person person = mapper.readValue(str, Person.class);
        System.out.println(person.toString());

        Map<String, Object> map = mapper.readValue(str, new TypeReference<Map<String, Object>>() {});
        System.out.println(map);

    }

    @Test
    public void jacksonTest_3() throws IOException {

        String strList = "[{\"user_name\":\"liuning\",\"age\":\"25\",\"height\":\"162\",\"money\":\"100000\"}]";
        List<Person> personList = mapper.readValue(strList, new TypeReference<List<Person>>() {});
        System.out.println(personList.size());

    }

    @Test
    public void jacksonTest_4() throws IOException {

        Person person = new Person();
        person.setUsername("Dick");
        person.setAge(25);
        person.setHeight(163);
        person.setMoney(new BigDecimal(5000));
        person.setDate(LocalDateTime.now());
        System.out.println(mapper.writeValueAsString(person));
    }

    public static class Person {

        @JsonProperty("user_name")
        private String username;

        private Integer age;

        @JsonIgnore
        private int height;

        private BigDecimal money;

        @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
        private LocalDateTime date;


        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public BigDecimal getMoney() {
            return money;
        }

        public void setMoney(BigDecimal money) {
            this.money = money;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "username='" + username + '\'' +
                    ", age=" + age +
                    ", height=" + height +
                    ", money=" + money +
                    ", date=" + date +
                    '}';
        }
    }
}
