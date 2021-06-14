package com.liuning.invoke.invoker;

/**
 * Invoker调用
 *
 * @author liuning
 * @since 2020-09-19 10:04
 */
@FunctionalInterface
public interface Invoker<R> {

    /**
     * 方法调用
     *
     * @return 原始返回
     * @throws Exception 异常
     */
    R invoke() throws Exception;
}
