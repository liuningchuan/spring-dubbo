package com.liuning.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liuning
 * @description IP工具类
 * @since 2020-07-23 23:18
 */
public class IPUtils {

    private static final String UNKNOWN = "unknown";

    /**
     * 从HttpServletRequest中获取IP
     *
     * @param request HttpServletRequest
     * @return IP
     */
    public static String getIPAddress(HttpServletRequest request) {
        String ip = null;
        //X-Forwarded-For：Squid 服务代理
        String ipAddresses = request.getHeader("X-Forwarded-For");
        if (isWrongIp(ipAddresses)) {
            ipAddresses = request.getHeader("Proxy-Client-IP");
        }
        if (isWrongIp(ipAddresses)) {
            ipAddresses = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isWrongIp(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isWrongIp(ipAddresses)) {
            //X-Real-IP：nginx服务代理
            ipAddresses = request.getHeader("X-Real-IP");
        }
        if (isWrongIp(ipAddresses)) {
            ipAddresses = request.getHeader("HTTP_X_FORWARDED_FOR");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (!StringUtils.isBlank(ipAddresses)) {
            ip = ipAddresses.split(",")[0];
        }
        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (isWrongIp(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 判断所取得的ip是否正确
     */
    private static boolean isWrongIp(String ip) {
        return (StringUtils.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip));
    }
}

