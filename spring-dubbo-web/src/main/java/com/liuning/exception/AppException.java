package com.liuning.exception;

public class AppException extends RuntimeException{

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
