package com.liuning.interfaces.dto.account;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @author: liuning
 * @description: 开户resDto
 * @create: 2020-06-15 23:28
 * @version: 1.0
 */
@Data
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

}
