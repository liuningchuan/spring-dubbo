package com.liuning.common.exception;

/**
 * @author: liuning
 * @description: 异常类
 * @create: 2020-06-08 22:31
 * @version: 1.0
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -4639254333683020280L;
    /**
     * 异常码
     */
    private String code;

    /**
     * 异常信息
     */
    private String message;

    public AppException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
