package com.liuning.interfaces.account.service;

import com.liuning.interfaces.account.entity.AccountOpenReqDto;
import com.liuning.interfaces.account.entity.AccountOpenResDto;

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
    AccountOpenResDto accountOpen(AccountOpenReqDto req);
}
