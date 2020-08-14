package com.liuning.common.utils;

import com.liuning.common.enums.ErrorCodeEnum;
import com.liuning.common.exception.AppException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: liuning
 * @description: Date Util
 * @create: 2020-07-22 00:14
 * @version: 1.0
 */
public class DateUtil {

    /**
     * 格式化日期
     *
     * @param date   需要格式化的Date对象
     * @param format 日期格式
     * @return 按照日期格式format得到的字符串
     */
    public static String format(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 将字符串转换成Date对象
     *
     * @param dateStr 需要转换的字符串
     * @param format  日期格式
     * @return 转换后得到的Date对象
     */
    public static Date parse(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new AppException(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getMessage());
        }
        return date;
    }

    /**
     * 获取当前时间
     *
     * @return Date对象
     */
    public static Date now() {
        return new Date();
    }

}
