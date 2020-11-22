package com.liuning.interfaces.dto.account;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: liuning
 * @description: 开户reqDto
 * @create: 2020-06-15 23:23
 * @version: 1.0
 */
public class AccountOpenReqDto implements Serializable {

    private static final long serialVersionUID = 8617520276258587504L;

    /**
     * 商户侧用户唯一id
     */
    @NotBlank(message = "商户侧用户唯一 ID不能为空")
    @Length(max = 40, message = "商户侧ID最长为40位")
    private String custMerchantNo;

    /**
     * 个人用户认证姓名
     */
    @NotBlank(message = "个人用户进行实名认证的姓名")
    @Length(max = 60, message = "个人认证姓名长度最长为60位")
    private String custName;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号必须和银行卡预留手机号保持一致")
    @Length(min = 11, max = 11, message = "手机号应该为11位")
    private String mobilePhoneNum;

    /**
     * 银行卡卡号
     */
    @NotBlank(message = "个人实名的借记卡卡号")
    @Length(max = 40, message = "借记卡卡号最长不能超过40位")
    private String acct;

    /**
     * 证件类型
     */
    @NotBlank(message = "枚举值： 0 表示二代身份证，目前仅支持该类")
    @Length(max = 1, message = "填入枚举值,目前仅支持二代身份证")
    private String custIdt;

    /**
     * 证件号码
     */
    @NotBlank(message = "与证件类型对应的证件号码，目前仅支持二代身份证")
    @Length(max = 18, message = "证件号码最长为18位")
    private String custIdNo;

    /**
     * 证件签发日期
     */
    @Length(max = 30, message = "身份证签发日期格式为：yyyy-MM-dd")
    private String cardStartDate;

    /**
     * 证件到期日期
     */
    @Length(max = 30, message = "身份证上的有效日期 格式：yyyy-MM-dd 如果长期有效则传入 ‘长期’")
    private String cardEndDate;

    public String getCustMerchantNo() {
        return custMerchantNo;
    }

    public void setCustMerchantNo(String custMerchantNo) {
        this.custMerchantNo = custMerchantNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getMobilePhoneNum() {
        return mobilePhoneNum;
    }

    public void setMobilePhoneNum(String mobilePhoneNum) {
        this.mobilePhoneNum = mobilePhoneNum;
    }

    public String getAcct() {
        return acct;
    }

    public void setAcct(String acct) {
        this.acct = acct;
    }

    public String getCustIdt() {
        return custIdt;
    }

    public void setCustIdt(String custIdt) {
        this.custIdt = custIdt;
    }

    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
    }

    public String getCardStartDate() {
        return cardStartDate;
    }

    public void setCardStartDate(String cardStartDate) {
        this.cardStartDate = cardStartDate;
    }

    public String getCardEndDate() {
        return cardEndDate;
    }

    public void setCardEndDate(String cardEndDate) {
        this.cardEndDate = cardEndDate;
    }

    @Override
    public String toString() {
        return "AccountOpenReqDto{" +
                "custMerchantNo='" + custMerchantNo + '\'' +
                ", custName='" + custName + '\'' +
                ", mobilePhoneNum='" + mobilePhoneNum + '\'' +
                ", acct='" + acct + '\'' +
                ", custIdt='" + custIdt + '\'' +
                ", custIdNo='" + custIdNo + '\'' +
                ", cardStartDate='" + cardStartDate + '\'' +
                ", cardEndDate='" + cardEndDate + '\'' +
                '}';
    }
}
