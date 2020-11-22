package com.liuning.interfaces.api.trade;

import com.liuning.interfaces.dto.BaseResponse;
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
    BaseResponse<PayForAnotherResDto> payForAnother(PayForAnotherReqDto req);

    /**
     * 提现
     * @param req WithdrawalsReqDto
     * @return WithdrawalsResDto
     */
    BaseResponse<WithdrawalsResDto> withdrawals(WithdrawalsReqDto req);

}
