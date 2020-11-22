package com.liuning.interfaces.dto;

/**
 * 通用返回对象
 *
 * @author liuning
 * @since 2020-11-22 23:25
 */
public class BaseResponse<T> {

    /**
     * 返回内容
     */
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
