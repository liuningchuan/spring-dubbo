package com.liuning.dto.trade;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: liuning
 * @description: 交易详情查询ResDto
 * @create: 2020-06-22 23:49
 * @version: 1.0
 */
@ToString
@Data
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
}
