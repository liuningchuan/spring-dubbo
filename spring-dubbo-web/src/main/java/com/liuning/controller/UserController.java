package com.liuning.controller;

import com.liuning.dubbo.entity.User;
import com.liuning.dubbo.service.UserDubboService;
import com.liuning.emum.StatusCode;
import com.liuning.mapper.UserMapper;
import com.liuning.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/demo")
@Api(tags = "Base Controller", value = "233")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserDubboService userDubboService;

    @Resource
    UserMapper userMapper;

    @GetMapping("/world")
    @ApiOperation(value = "2323", notes = "2333")
    public List<com.liuning.entity.User> getUser() {

        logger.trace("Hello World!");
        logger.debug("How are you today?");
        logger.info("I am fine.");
        logger.warn("I love programming.");
        logger.error("I am programming.");

        User user = new User();
        user.setEmail("599522516@qq.com");
        user.setUsername("LiuNing");
        user.setPassword("open1234");
        logger.info("result is {}", user);
        return userMapper.selectAll();
//        return userDubboService.saveUser(user);
    }

    @GetMapping("/result")
    @ApiOperation(value = "Result测试", notes = "返回成功")
    public Result result(){
        return new Result(StatusCode.SUCCESS);
    }

    @GetMapping("/result1")
    @ApiOperation(value = "Result测试", notes = "返回数据")
    public Result result1(){
        User user = new User();
        user.setEmail("599522516@qq.com");
        user.setUsername("LiuNing");
        user.setPassword("open1234");
        return new Result<>(0, "请求成功", user);
    }

    /**
     * 直接返回字符串
     * @return string
     */
    @GetMapping("/string")
    public String getString() {
        return "SUCCESS";
    }

}