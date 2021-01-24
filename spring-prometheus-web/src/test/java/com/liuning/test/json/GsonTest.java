package com.liuning.test.json;

import com.google.gson.*;
import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;

/**
 * @author liuning
 * @description Google Gson Test
 * @since 2020-08-07 00:03
 */
public class GsonTest {

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


        DateExample2 dateExample3 = GSON.fromJson(dateString2, DateExample2.class);
        System.out.println("date is " + GSON.toJson(dateExample3));

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


    private static class DateExample2 {

        public String name;

        public String description;

        public String date;

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

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
