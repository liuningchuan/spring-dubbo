package com.liuning.interfaces.api.account;

import com.liuning.interfaces.dto.BaseResponse;
import com.liuning.interfaces.dto.account.AccountOpenReqDto;
import com.liuning.interfaces.dto.account.AccountOpenResDto;

/**
 * @author: liuning
 * @description: 账户类服务
 * @create: 2020-06-15 23:07
 * @version: 1.0
 */
public interface AccountDubboService {

    /**
     * 账户开户
     * @param req AccountOpenReqDto
     * @return AccountOpenResDto
     */
    BaseResponse<AccountOpenResDto> accountOpen(AccountOpenReqDto req);
}
