package com.liuning.web.controller;

import com.liuning.dao.entity.User;
import com.liuning.dao.entity.UserExample;
import com.liuning.dao.mapper.UserMapper;
import com.liuning.web.aspect.BusiLog;
import com.liuning.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    UserMapper userMapper;

    @GetMapping("/world/{start}/{end}")
    @BusiLog(name = "测试接口",ignore = true)
    public List<User> getUser(@PathVariable int start, @PathVariable int end, @RequestParam(required = false) String name) {

        Assert.notNull(name, "URI is required");

        log.info("start is {}, end is {}", start, end);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdBetween(start, end);
        return userMapper.selectByExample(userExample);
    }

    @GetMapping("/result")
    @BusiLog(name = "测试接口",ignore = true)
    public Result<?> result(){
        return Result.success();
    }

    @PostMapping("/result")
    @BusiLog(name = "测试接口",ignore = true)
    public Result<User> result1(@RequestBody @NonNull User user){
        return Result.success(user);
    }

    @GetMapping("/string")
    @BusiLog(name = "返回字符串",ignore = true)
    public String getString() {
        return "SUCCESS";
    }

}