package com.liuning.controller;

import com.liuning.aspect.BusiLog;
import com.liuning.entity.User;
import com.liuning.dubbo.service.UserDubboService;
import com.liuning.enums.ErrorCodeEnums;
import com.liuning.entity.UserExample;
import com.liuning.mapper.UserMapper;
import com.liuning.model.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/test")
@Api(tags = "Base Controller", value = "测试接口")
public class TsetController {

    private static final Logger log = LoggerFactory.getLogger(TsetController.class);

    @Reference
    private UserDubboService userDubboService;

    @Resource
    UserMapper userMapper;

    @GetMapping("/world/{start}/{end}")
    @ApiOperation(value = "2323", notes = "2333")
    @BusiLog(name = "测试接口",ignore = true)
    public List<User> getUser(@PathVariable int start, @PathVariable int end, @RequestParam(required = false) String name) {

        Assert.notNull(name, "URI is required");

        log.info("start is {}, end is {}", start, end);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdBetween(start, end);
        return userMapper.selectByExample(userExample);
    }

    @GetMapping("/result")
    @ApiOperation(value = "Result测试", notes = "返回成功")
    public Result result(){
        return new Result(ErrorCodeEnums.SUCCESS);
    }

    @GetMapping("/result1")
    @ApiOperation(value = "Result测试", notes = "返回数据")
    public Result result1(){
        User user = new User();
        user.setEmail("599522516@qq.com");
        user.setName("LiuNing");
        user.setPassword("open1234");
        return new Result<>("0000000", "请求成功", user);
    }

    /**
     * 直接返回字符串
     * @return string
     */
    @GetMapping("/string")
    public String getString() {
        return "SUCCESS";
    }

}