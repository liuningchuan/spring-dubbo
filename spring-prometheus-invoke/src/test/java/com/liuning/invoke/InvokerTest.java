package com.liuning.invoke;

import com.liuning.invoke.invoker.BaseResponse;
import com.liuning.invoke.invoker.GenericInvokerTemplate;
import com.liuning.invoke.trade.TradeInvoker;
import org.junit.Assert;
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
@ContextConfiguration(classes = TradeInvoker.class)
@RunWith(SpringRunner.class)
public class InvokerTest {

    @Autowired
    private TradeInvoker tradeInvoker;

    @Test
    public void test() {
        BaseResponse<String> tradeDetail = GenericInvokerTemplate.invoke(
                "交易详情查询接口",
                () -> tradeInvoker.tradeDetailQuery("233"),
                result -> true
        );
        Assert.assertNotNull(tradeDetail);
        Assert.assertTrue(tradeDetail.isSuccess());
        Assert.assertEquals("000000", tradeDetail.getCode());
        System.out.println(tradeDetail);
    }
}
