package com.liuning.interfaces.api.trade;

import com.liuning.interfaces.common.Result;
import com.liuning.interfaces.dto.trade.PayForAnotherReqDto;
import com.liuning.interfaces.dto.trade.PayForAnotherResDto;
import com.liuning.interfaces.dto.trade.WithdrawalsReqDto;
import com.liuning.interfaces.dto.trade.WithdrawalsResDto;

public interface TradeDubboService {

    /**
     * 代付到卡
     * @param req PayForAnotherReqDto
     * @return PayForAnotherResDto
     */
    Result<PayForAnotherResDto> payForAnother(PayForAnotherReqDto req);

    /**
     * 提现
     * @param req WithdrawalsReqDto
     * @return WithdrawalsResDto
     */
    Result<WithdrawalsResDto> withdrawals(WithdrawalsReqDto req);

}
