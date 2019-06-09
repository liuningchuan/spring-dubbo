package com.liuning.dubbo.service;

import com.liuning.dubbo.entity.User;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Reference
    private UserService userService;

    public User saveUser() {
        User user = new User();
        user.setEmail("599522516@qq.com");
        user.setUsername("LiuNing");
        user.setPassword("open123");
        return userService.saveUser(user);
    }
}
