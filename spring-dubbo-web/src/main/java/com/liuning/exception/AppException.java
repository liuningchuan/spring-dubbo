package com.liuning.exception;

public class AppException extends RuntimeException{

    /**
     * 异常码
     */
    private Integer code;

    /**
     * 异常信息
     */
    private String message;

    public AppException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
