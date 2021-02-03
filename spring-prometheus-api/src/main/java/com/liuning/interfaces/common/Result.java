package com.liuning.interfaces.common;

/**
 * 通用返回对象
 *
 * @author liuning
 * @since 2020-11-22 23:25
 */
public class Result<T> {

    private static final String SUCCESS_CODE = "000000";
    private static final String SUCCESS_MESSAGE = "请求成功";

    /**
     * 响应码值
     */
    private String code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 返回内容
     */
    private T data;

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MESSAGE);
        result.setData(null);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(SUCCESS_CODE);
        result.setMessage(SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        return result;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
