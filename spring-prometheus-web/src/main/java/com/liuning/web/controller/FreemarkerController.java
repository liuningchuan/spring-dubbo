package com.liuning.web.controller;

import com.liuning.common.utils.ValidationUtils;
import com.liuning.dto.Request;
import com.liuning.dto.Result;
import com.liuning.dto.trade.TradeDetailQueryReqDto;
import com.liuning.dto.trade.TradeDetailQueryResDto;
import com.liuning.web.aspect.BusiLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
class FreemarkerController {

    @RequestMapping("/index")
    @BusiLog(name = "Freemarker测试接口", ignore = true)
    public String index(Model model){
        model.addAttribute("name","hello pillar");
        return "index.ftl";
    }

    @PostMapping("/trade/detail")
    @BusiLog(name = "交易详情查询接口", ignore = true)
    public Result<TradeDetailQueryResDto> tradeDetailQuery(@RequestBody Request<TradeDetailQueryReqDto> req) {
        ValidationUtils.validate(req);
        System.out.println(req.getData().getTradeNo());
        return Result.success();
    }
}
