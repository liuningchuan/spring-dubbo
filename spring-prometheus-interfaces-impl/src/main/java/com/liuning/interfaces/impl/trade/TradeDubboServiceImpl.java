package com.liuning.interfaces.impl.trade;

import com.liuning.common.constants.DubboProviderConstants;
import com.liuning.interfaces.trade.entity.PayForAnotherReqDto;
import com.liuning.interfaces.trade.entity.PayForAnotherResDto;
import com.liuning.interfaces.trade.entity.WithdrawalsReqDto;
import com.liuning.interfaces.trade.entity.WithdrawalsResDto;
import com.liuning.interfaces.trade.service.TradeDubboService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author: liuning
 * @description: 交易类接口实现
 * @create: 2020-06-14 22:48
 * @version: 1.0
 */
@Service(group = DubboProviderConstants.DEFAULT_GROUP,
        version = DubboProviderConstants.DEFAULT_VERSION,
        timeout = DubboProviderConstants.DEFAULT_TIMEOUT,
        retries = DubboProviderConstants.DEFAULT_RETRY)
public class TradeDubboServiceImpl implements TradeDubboService {

    @Override
    public PayForAnotherResDto payForAnother(PayForAnotherReqDto req) {
        return null;
    }

    @Override
    public WithdrawalsResDto withdrawals(WithdrawalsReqDto req) {
        return null;
    }
}
