package com.liuning.interfaces.dto.trade;

import java.io.Serializable;

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

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    @Override
    public String toString() {
        return "PayForAnotherResDto{" +
                "respCode='" + respCode + '\'' +
                ", respMsg='" + respMsg + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                '}';
    }
}
