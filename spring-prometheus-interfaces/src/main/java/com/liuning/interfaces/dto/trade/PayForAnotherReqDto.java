package com.liuning.interfaces.dto.trade;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class PayForAnotherReqDto implements Serializable {

    private static final long serialVersionUID = -6367177694796093861L;

    /**
     * 日志流水号
     */
    @NotBlank(message = "日志流水号不能为空")
    @Length(max = 40, message = "日志流水号最长为40位")
    private String clientSerialNo;

    /**
     * 商户号
     */
    @NotBlank(message = "商户号不能为空")
    @Length(max = 32, message = "商户号最长为32位")
    private String merchantNo;

    /**
     * 交易订单号
     */
    @NotBlank(message = "交易订单号不能为空")
    private String tradeNo;

    /**
     * 交易金额
     */
    @NotBlank(message = "交易金额不能为空")
    private String tradeAmount;

    /**
     * 出账方姓名
     */
    @NotBlank(message = "出账方姓名不能为空")
    private String nameOut;

    /**
     * 出账方账号
     */
    @NotBlank(message = "出账方账号不能为空")
    private String accountOut;

    /**
     * 入账方姓名
     */
    @NotBlank(message = "入账方姓名不能为空")
    private String nameIn;

    /**
     * 入账方账号
     */
    @NotBlank(message = "入账方账号不能为空")
    private String accountIn;

    /**
     * 交易备注
     */
    private String tradeRemark;

    public String getClientSerialNo() {
        return clientSerialNo;
    }

    public void setClientSerialNo(String clientSerialNo) {
        this.clientSerialNo = clientSerialNo;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(String tradeAmount) {
        this.tradeAmount = tradeAmount;
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
        return "PayForAnotherReqDto{" +
                "clientSerialNo='" + clientSerialNo + '\'' +
                ", merchantNo='" + merchantNo + '\'' +
                ", tradeNo='" + tradeNo + '\'' +
                ", tradeAmount='" + tradeAmount + '\'' +
                ", nameOut='" + nameOut + '\'' +
                ", accountOut='" + accountOut + '\'' +
                ", nameIn='" + nameIn + '\'' +
                ", accountIn='" + accountIn + '\'' +
                ", tradeRemark='" + tradeRemark + '\'' +
                '}';
    }
}
