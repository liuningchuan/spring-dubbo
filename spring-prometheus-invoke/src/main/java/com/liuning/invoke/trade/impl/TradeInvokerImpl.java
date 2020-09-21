package com.liuning.invoke.trade.impl;

import com.liuning.invoke.trade.TradeInvoker;
import org.springframework.stereotype.Service;

/**
 * TradeInvokerImpl
 *
 * @author liuning
 * @since 2020-09-21 22:58
 */
@Service
public class TradeInvokerImpl implements TradeInvoker {

    @Override
    public String tradeDetailQuery(String tradeNo) {
        return "HelloWorld";
    }
}
