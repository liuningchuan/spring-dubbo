package com.liuning.service;

import com.liuning.entity.User;
import com.liuning.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
