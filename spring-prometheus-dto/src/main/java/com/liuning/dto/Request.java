package com.liuning.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class Request<T> implements Serializable {

    private static final long serialVersionUID = 7120556038442943652L;

    /**
     * 日志流水号
     */
    @NotBlank(message = "日志流水号不能为空")
    private String clientSerialNo;

    /**
     * 服务名称
     */
    @NotBlank(message = "调用服务不能为空")
    private String service;

    /**
     * 请求数据
     */
    @Valid
    private T data;

    public String getClientSerialNo() {
        return clientSerialNo;
    }

    public void setClientSerialNo(String clientSerialNo) {
        this.clientSerialNo = clientSerialNo;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
