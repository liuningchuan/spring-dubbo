package com.liuning.invoke.invoker;

/**
 * Response Checker
 *
 * @author liuning
 * @since 2020-09-26 21:45
 */
@FunctionalInterface
public interface ResponseChecker<R> {

    boolean isSuccess(R result);
}
