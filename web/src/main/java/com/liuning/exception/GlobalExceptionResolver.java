package com.liuning.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理
 * @author liuning
 * @date 2019-10-01 23:27
 */
public class GlobalExceptionResolver {

    @ExceptionHandler(value = AppException.class)
    public String AppException() {
        return "";
    }
}
