package com.liuning.interfaces.account.entity;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @author: liuning
 * @description: 开户resDto
 * @create: 2020-06-15 23:28
 * @version: 1.0
 */
public class AccountOpenResDto implements Serializable {

    private static final long serialVersionUID = -4845747278112129026L;

    /**
     * 商户侧会员号
     */
    @Length(max = 40, message = "商户侧号最长为40位")
    private String custMerchantNo;

    /**
     * 账号
     */
    @Length(max = 22, message = "账号最长为22位")
    private String accountNo;

    public String getCustMerchantNo() {
        return custMerchantNo;
    }

    public void setCustMerchantNo(String custMerchantNo) {
        this.custMerchantNo = custMerchantNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Override
    public String toString() {
        return "AccountOpenResDto{" +
                "custMerchantNo='" + custMerchantNo + '\'' +
                ", accountNo='" + accountNo + '\'' +
                '}';
    }
}
