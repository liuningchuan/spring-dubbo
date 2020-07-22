package com.liuning.web.controller;

import com.liuning.common.enums.ErrorCodeEnum;
import com.liuning.common.exception.AppException;
import com.liuning.common.utils.ParamValidation;
import com.liuning.common.utils.ValidationUtils;
import com.liuning.dto.Request;
import com.liuning.dto.Result;
import com.liuning.dto.trade.TradeDetailQueryReqDto;
import com.liuning.dto.trade.TradeDetailQueryResDto;
import com.liuning.web.aspect.BusiLog;
import com.liuning.web.concurrent.ParallelProcessor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

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

        //同事写的一个不可取的并行校验方法
        ParallelProcessor<Request<TradeDetailQueryReqDto>> parallelProcessor =
                new ParallelProcessor<>(new HashMap<>(),req);
        parallelProcessor.addChecker("校验服务", this::checkService)
                .addChecker("校验日志流水号",this::checkClientSrial)
                .addChecker("校验交易流水号", this::checkTradeNo)
                .executeAll();

        ValidationUtils.validate(req, ParamValidation.class);
        System.out.println(req.getData().getTradeNo());
        return Result.success();
    }

    private void checkService(Request<TradeDetailQueryReqDto> tradeDetailQueryReqDtoRequest) {
        if (!tradeDetailQueryReqDtoRequest.getService().equals("trade")) {
            throw new AppException(ErrorCodeEnum.PARAM_ERROR.getCode(), ErrorCodeEnum.PARAM_ERROR.getMessage());
        }
    }

    private void checkClientSrial(Request<TradeDetailQueryReqDto> tradeDetailQueryReqDtoRequest) {
        if (!tradeDetailQueryReqDtoRequest.getClientSerialNo().equals("trade")) {
            throw new AppException(ErrorCodeEnum.PARAM_ERROR.getCode(), ErrorCodeEnum.PARAM_ERROR.getMessage());
        }
    }

    private void checkTradeNo(Request<TradeDetailQueryReqDto> tradeDetailQueryReqDtoRequest) {
        if (!tradeDetailQueryReqDtoRequest.getData().getTradeNo().equals("trade")) {
            throw new AppException(ErrorCodeEnum.PARAM_ERROR.getCode(), ErrorCodeEnum.PARAM_ERROR.getMessage());
        }
    }

}
