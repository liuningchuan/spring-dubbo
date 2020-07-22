package com.liuning.dto.trade;

import com.liuning.common.utils.ParamValidation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author: liuning
 * @description: 交易详情查询ReqDto
 * @create: 2020-06-22 23:40
 * @version: 1.0
 */
public class TradeDetailQueryReqDto implements Serializable {

    private static final long serialVersionUID = 2277646640039608858L;

    @NotBlank(message = "交易流水号不能为空", groups = {ParamValidation.class})
    @Length(max = 32, message = "交易流水号长度不能超过32位")
    private String tradeNo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }
}
