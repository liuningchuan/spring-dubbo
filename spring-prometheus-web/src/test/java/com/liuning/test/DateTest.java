package com.liuning.test;

import com.liuning.common.utils.DateFormatUtil;
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

    @Test
    public void dateUtil() {
        System.out.println(DateFormatUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println(DateFormatUtil.parse("2020-08-14 23:53:37:244", "yyyy-MM-dd HH:mm:ss:SSS"));
        System.out.println(DateFormatUtil.now());
    }

    @Test
    public void dateFormateUtil() {
        System.out.println(DateFormatUtil.formate("20201015", "yyyyMMdd", "yyyy-MM-dd"));
    }
}
