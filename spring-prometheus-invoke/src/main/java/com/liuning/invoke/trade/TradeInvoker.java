package com.liuning.invoke.trade;

/**
 * Trade Invoker
 *
 * @author liuning
 * @since 2020-09-19 09:57
 */
public interface TradeInvoker {

    /**
     * 交易详情查询
     *
     * @param tradeNo 交易流水号
     * @return 交易详情
     */
    String tradeDetailQuery(String tradeNo);
}
