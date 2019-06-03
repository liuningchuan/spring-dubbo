package com.liuning.dubbo.service;


import com.alibaba.dubbo.config.annotation.Reference;
import com.liuning.dubbo.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserDubboService {

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
