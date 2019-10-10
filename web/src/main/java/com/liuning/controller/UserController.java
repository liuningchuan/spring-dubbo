package com.liuning.controller;

import com.liuning.dubbo.entity.User;
import com.liuning.dubbo.service.UserDubboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@Api(tags = "基础服务中心", value = "233")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Reference
    private UserDubboService userDubboService;

    @GetMapping("/world")
    @ApiOperation(value = "2323", notes = "2333")
    public User getUser() {

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
        return userDubboService.saveUser(user);
    }
}