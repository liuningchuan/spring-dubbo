package com.liuning.interfaces.impl.account;

import com.liuning.interfaces.dto.BaseResponse;
import com.liuning.interfaces.dto.account.AccountOpenReqDto;
import com.liuning.interfaces.dto.account.AccountOpenResDto;
import com.liuning.interfaces.api.account.AccountDubboService;

/**
 * @author: liuning
 * @description: 账户类接口实现
 * @create: 2020-06-15 23:31
 * @version: 1.0
 */
public class AccountDubboServiceImpl implements AccountDubboService {

    @Override
    public BaseResponse<AccountOpenResDto> accountOpen(AccountOpenReqDto req) {
        return null;
    }
}
