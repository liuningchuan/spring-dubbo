package com.liuning.interfaces.dto.trade;

import lombok.Data;

import java.io.Serializable;

@Data
public class PayForAnotherResDto implements Serializable {

    private static final long serialVersionUID = 3463317497663052545L;

    /**
     * 返回码
     */
    private String respCode;

    /**
     * 返回信息
     */
    private String respMsg;

    /**
     * 交易流水号
     */
    private String tradeNo;

    /**
     * 交易状态
     * 0处理中 1成功2 失败3 拒绝4 取消
     */
    private String tradeStatus;

}
