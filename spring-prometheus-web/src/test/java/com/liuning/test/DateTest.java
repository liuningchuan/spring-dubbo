package com.liuning.test;

import com.liuning.common.utils.JsonUtils;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author: liuning
 * @description: Date Test
 * @create: 2020-07-21 23:19
 * @version: 1.0
 */
public class DateTest {

    @Test
    public void date_test_1() {
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);
        System.out.println(JsonUtils.toJson(date));

        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
    }
}
