package com.liuning.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.liuning.dubbo.entity.User;

@Service
public class UserService {

    public User saveUser(User user) {

        System.out.println(user.toString());
        return user;
    }
}
