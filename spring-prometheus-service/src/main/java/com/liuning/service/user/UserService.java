package com.liuning.service.user;

import com.liuning.dao.entity.User;
import com.liuning.dao.entity.UserDetail;
import com.liuning.dao.mapper.UserDetailMapper;
import com.liuning.dao.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

/**
 * UserService
 *
 * @author liuning
 * @since 2020-10-18 22:16
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    TransactionTemplate transactionTemplate;

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserDetailMapper userDetailMapper;

    /**
     * spring声明式事务
     */
    @Transactional(rollbackFor = {Exception.class})
    public void insertUser() {
        User user = new User();
        user.setName("韩立");
        user.setPassword("qwer");
        user.setEmail("xxx@qq.com");
        userMapper.insert(user);

        UserDetail userDetail = new UserDetail();
        userDetail.setName("韩立");
        userDetail.setJob("灵界第一大乘天南第一剑修凡人修仙传主角");
        userDetail.setAddress("天南");
        userDetailMapper.insertSelective(userDetail);
    }

    /**
     * spring编程式事务
     */
    public void insertUsers() {
        User user = new User();
        user.setName("韩立");
        user.setPassword("qwer");
        user.setEmail("xxx@qq.com");

        UserDetail userDetail = new UserDetail();
        userDetail.setName("韩立");
        userDetail.setJob("灵界第一大乘天南第一剑修凡人修仙传主角");
        userDetail.setAddress("天南");

        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    userMapper.insert(user);
                    userDetailMapper.insertSelective(userDetail);
                } catch (Exception e) {
                    log.error("插入数据库异常", e);
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }
}
