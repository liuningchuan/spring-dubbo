package com.liuning.dto.trade;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: liuning
 * @description: 交易详情查询ResDto
 * @create: 2020-06-22 23:49
 * @version: 1.0
 */
public class TradeDetailQueryResDto implements Serializable {

    private static final long serialVersionUID = 6467654784832109341L;

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 交易状态 0-处理中 1-成功 2-失败
     */
    private String tradeStatus;

    /**
     * 交易流水号
     */
    private String tradeNo;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmount;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 出账方姓名
     */
    private String nameOut;

    /**
     * 出账方账号
     */
    private String accountOut;

    /**
     * 入账方姓名
     */
    private String nameIn;

    /**
     * 入账方账号
     */
    private String accountIn;

    /**
     * 交易备注
     */
    private String tradeRemark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getNameOut() {
        return nameOut;
    }

    public void setNameOut(String nameOut) {
        this.nameOut = nameOut;
    }

    public String getAccountOut() {
        return accountOut;
    }

    public void setAccountOut(String accountOut) {
        this.accountOut = accountOut;
    }

    public String getNameIn() {
        return nameIn;
    }

    public void setNameIn(String nameIn) {
        this.nameIn = nameIn;
    }

    public String getAccountIn() {
        return accountIn;
    }

    public void setAccountIn(String accountIn) {
        this.accountIn = accountIn;
    }

    public String getTradeRemark() {
        return tradeRemark;
    }

    public void setTradeRemark(String tradeRemark) {
        this.tradeRemark = tradeRemark;
    }

    @Override
    public String toString() {
        return "TradeDetailQueryResDto{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", tradeAmount=" + tradeAmount +
                ", tradeType='" + tradeType + '\'' +
                ", nameOut='" + nameOut + '\'' +
                ", accountOut='" + accountOut + '\'' +
                ", nameIn='" + nameIn + '\'' +
                ", accountIn='" + accountIn + '\'' +
                ", tradeRemark='" + tradeRemark + '\'' +
                '}';
    }
}
