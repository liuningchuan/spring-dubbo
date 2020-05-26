package com.liuning.enums;

public enum ErrorCodeEnums {

    SUCCESS("000000","请求成功"),
    FAIL("1","失败"),
    PARAM_ERROR("100100", "参数校验失败");

    private String code;
    private String message;

    ErrorCodeEnums(String code, String message) {
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
