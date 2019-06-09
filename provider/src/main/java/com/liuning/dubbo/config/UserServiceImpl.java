package com.liuning.dubbo.config;

import com.liuning.dubbo.service.UserService;
import com.liuning.dubbo.entity.User;
import org.apache.dubbo.config.annotation.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User saveUser(User user) {
        System.out.println(user.toString());
        return user;
    }
}
