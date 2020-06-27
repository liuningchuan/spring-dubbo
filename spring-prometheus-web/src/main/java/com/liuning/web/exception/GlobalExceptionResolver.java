package com.liuning.web.exception;

import com.liuning.common.enums.ErrorCode;
import com.liuning.common.exception.AppException;
import com.liuning.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author liuning
 * @date 2019-10-01 23:27
 */
@ControllerAdvice
public class GlobalExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public Result<?> AppException(AppException e) {
        log.warn("接口调用失败：", e);
        return Result.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> Exception(Exception e) {
        log.error("接口调用异常", e);
        return Result.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMessage());
    }
}
