package com.liuning.controller;

import com.liuning.dubbo.entity.User;
import com.liuning.dubbo.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Reference
    private UserService userDubboService;

    @GetMapping("/world")
    public User getUser() {
        User user = new User();
        user.setEmail("599522516@qq.com");
        user.setUsername("LiuNing");
        user.setPassword("open1234");
        return userDubboService.saveUser(user);
    }
}