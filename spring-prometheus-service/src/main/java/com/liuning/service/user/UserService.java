package com.liuning.service.user;

import com.liuning.dao.entity.User;
import com.liuning.dao.entity.UserDetail;
import com.liuning.dao.mapper.UserDetailMapper;
import com.liuning.dao.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
    private PlatformTransactionManager txManager;

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

        //设置事务传播属性
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        // 设置事务的隔离级别,设置为读已提交（默认是ISOLATION_DEFAULT:使用的是底层数据库的默认的隔离级别）
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        // 设置是否只读，默认是false
        transactionTemplate.setReadOnly(true);
        // 默认使用的是数据库底层的默认的事务的超时时间
        transactionTemplate.setTimeout(30000);
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

        transactionTemplate.execute(transactionStatus -> {
            try {
                userMapper.insert(user);
                userDetailMapper.insertSelective(userDetail);
            } catch (Exception e) {
                log.error("插入数据库异常", e);
                transactionStatus.setRollbackOnly();
                throw e;
            }
            return true;
        });
    }

    /**
     * spring编程式事务
     */
    public void insertSelective() {
        User user = new User();
        user.setName("韩立");
        user.setPassword("qwer");
        user.setEmail("xxx@qq.com");

        UserDetail userDetail = new UserDetail();
        userDetail.setName("韩立");
        userDetail.setJob("灵界第一大乘天南第一剑修凡人修仙传主角");
        userDetail.setAddress("天南");

        transactionTemplate.execute(status -> {
            userMapper.insert(user);
            userDetailMapper.insertSelective(userDetail);

            return true;
        });
    }

    public void insert(){

        User user = new User();
        user.setName("韩立");
        user.setPassword("qwer");
        user.setEmail("xxx@qq.com");

        UserDetail userDetail = new UserDetail();
        userDetail.setName("韩立");
        userDetail.setJob("灵界第一大乘天南第一剑修凡人修仙传主角");
        userDetail.setAddress("天南");

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //事务状态类，通过PlatformTransactionManager的getTransaction方法根据事务定义获取；
        // 获取事务状态后，Spring根据传播行为来决定如何开启事务
        TransactionStatus status = txManager.getTransaction(def);
        try {
            userMapper.insert(user);
            userDetailMapper.insertSelective(userDetail);
            txManager.commit(status);
        }catch (Exception e) {
            log.error("插入数据库异常", e);
            txManager.rollback(status);
        }
    }
}
