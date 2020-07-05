package com.liuning.common.enums;

/**
 * @author: liuning
 * @description: 错误码枚举
 * @create: 2020-06-08 22:39
 * @version: 1.0
 */
public enum ErrorCodeEnum {

    SUCCESS("000000","请求成功"),
    FAIL("1","失败"),
    PARAM_ERROR("100100", "参数校验失败"),
    SYSTEM_ERROR("999999","系统异常");

    private final String code;
    private final String message;

    ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorCodeEnum getValueOf(String code) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            if (errorCodeEnum.getCode().equals(code)) {
                return errorCodeEnum;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

