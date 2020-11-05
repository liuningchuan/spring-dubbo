package com.liuning.service;

import com.liuning.dao.mapper.UserMapper;
import com.liuning.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * spring编程式事务和声明式事务测试
 *
 * @author liuning
 * @since 2020-10-18 23:01
 */
@ContextConfiguration(classes = {TestComponentScanConfig.class, MybatisAutoConfiguration.class})
@RunWith(SpringRunner.class)
public class TransactionalTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.insertUser();
    }
}
