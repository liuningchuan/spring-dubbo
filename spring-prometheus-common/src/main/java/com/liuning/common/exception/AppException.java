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
    private String msg;

    public AppException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
