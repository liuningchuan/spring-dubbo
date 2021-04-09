package com.liuning.common.utils;

import com.liuning.common.enums.ErrorCodeEnum;
import com.liuning.common.exception.AppException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author: liuning
 * @description: Date Util
 * @create: 2020-07-22 00:14
 * @version: 1.0
 */
public class DateFormatUtil {

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
     * 格式化日期
     *
     * @param localDateTime 需要格式化的Date对象
     * @param format        日期格式
     * @return 按照日期格式format得到的字符串
     */
    public static String format(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }

    /**
     * 将日期格式为originFormatter的字符串转换成targetFormatter格式的字符串
     *
     * @param dateTime        日期字符串
     * @param originFormatter 转换前的日期格式
     * @param targetFormatter 转换后的日期格式
     * @return 转换后的日期字符串
     */
    public static String formate(String dateTime, String originFormatter, String targetFormatter) {
        LocalDate localDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(originFormatter));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(targetFormatter);
        return formatter.format(localDate);
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

    /**
     * LocalDate -> Date
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime -> Date
     *
     * @param localDateTime LocalDateTime
     * @return Date
     */
    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date -> LocalDate
     *
     * @param date Date
     * @return LocalDate
     */
    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date -> LocalDateTime
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
