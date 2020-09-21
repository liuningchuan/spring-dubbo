package com.liuning.invoke;

import com.liuning.invoke.invoker.GenericInvokerTemplate;
import com.liuning.invoke.trade.TradeInvoker;
import com.liuning.invoke.trade.impl.TradeInvokerImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Invoker测试
 *
 * @author liuning
 * @since 2020-09-21 22:44
 */
@ContextConfiguration(classes = TradeInvokerImpl.class)
@RunWith(SpringRunner.class)
public class InvokerTest {

    @Autowired
    private TradeInvoker tradeInvoker;

    @Test
    public void test() {
        String tradeDetail = GenericInvokerTemplate.invoke(
                () -> tradeInvoker.tradeDetailQuery("233")
        );
        System.out.println(tradeDetail);
    }
}
