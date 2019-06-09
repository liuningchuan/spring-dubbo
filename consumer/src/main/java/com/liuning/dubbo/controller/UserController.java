package com.liuning.dubbo.controller;

import com.liuning.dubbo.entity.User;
import com.liuning.dubbo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userDubboService;

    @GetMapping("/world")
    public User getUser() {
        return userDubboService.saveUser();
    }
}
