package com.liuning.invoke.invoker;

/**
 * 通用调用
 *
 * @author liuning
 * @since 2020-09-19 10:31
 */
public class GenericInvokerTemplate {

    public static <R, T> R invoke(Invoker<R> invoker) {
        R response = null;
        try {
            response =invoker.invoke();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
