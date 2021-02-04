package com.liuning.test.function;

import com.liuning.dao.entity.UserExample;
import com.liuning.dao.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class FunctionInterfaceTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void main() {
        test(()->{
            UserExample userExample = new UserExample();
            userExample.createCriteria().andIdBetween(0, 2);
            System.out.println(userMapper.selectByExample(userExample));
        });

        FunctionTest test = () -> System.out.println("Test again");
        test.doExecute();
        test.defaultMethod();
    }

    public void test(FunctionTest function) {
        function.doExecute();
        function.defaultMethod();
        FunctionTest.staticMethod();
    }

}
