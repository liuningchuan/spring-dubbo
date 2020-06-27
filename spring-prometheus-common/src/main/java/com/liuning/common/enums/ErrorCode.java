package com.liuning.common.enums;

/**
 * @author: liuning
 * @description: 错误码枚举
 * @create: 2020-06-08 22:39
 * @version: 1.0
 */
public enum ErrorCode {

    SUCCESS("000000","请求成功"),
    FAIL("1","失败"),
    PARAM_ERROR("100100", "参数校验失败"),
    SYSTEM_ERROR("999999","系统异常");

    private String code;
    private String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

