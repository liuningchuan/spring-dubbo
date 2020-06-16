package com.liuning.interfaces.trade.service;

import com.liuning.interfaces.trade.entity.PayForAnotherReqDto;
import com.liuning.interfaces.trade.entity.PayForAnotherResDto;
import com.liuning.interfaces.trade.entity.WithdrawalsReqDto;
import com.liuning.interfaces.trade.entity.WithdrawalsResDto;

public interface TradeDubboService {

    /**
     * 代付到卡
     * @param req PayForAnotherReqDto
     * @return PayForAnotherResDto
     */
    PayForAnotherResDto payForAnother(PayForAnotherReqDto req);

    /**
     * 提现
     * @param req WithdrawalsReqDto
     * @return WithdrawalsResDto
     */
    WithdrawalsResDto withdrawals(WithdrawalsReqDto req);

}
