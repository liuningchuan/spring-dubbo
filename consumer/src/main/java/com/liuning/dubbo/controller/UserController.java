package com.liuning.dubbo.controller;

import com.liuning.dubbo.entity.User;
import com.liuning.dubbo.service.UserDubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserDubboService userDubboService;

    public User getUser() {
        return userDubboService.saveUser();
    }
}
