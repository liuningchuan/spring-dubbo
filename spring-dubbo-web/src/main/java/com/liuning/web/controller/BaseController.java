package com.liuning.web.controller;

import com.liuning.common.utils.SpringApplicationContextHolder;
import com.liuning.dto.Result;
import com.liuning.service.BaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @PostMapping(value = "/request", consumes = "application/json;charset=UTF-8")
    public Result execute(@RequestBody String body) {
        long startTime = System.currentTimeMillis();

        BaseService baseService = SpringApplicationContextHolder.getBean("233", BaseService.class);
        Result result = baseService.execute();

        return result;
    }

}
