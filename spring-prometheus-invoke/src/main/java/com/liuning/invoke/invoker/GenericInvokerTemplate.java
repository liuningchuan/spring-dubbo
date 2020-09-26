package com.liuning.invoke.invoker;

/**
 * 通用调用
 *
 * @author liuning
 * @since 2020-09-19 10:31
 */
public class GenericInvokerTemplate {

    public static <R, T> BaseResponse<R> invoke(String desc,
                                                Invoker<R> invoker,
                                                ResponseChecker<R> responseChecker) {

        BaseResponse<R> response = null;
        try {
            R rawResponse =invoker.invoke();
            if (responseChecker.isSuccess(rawResponse)) {
                return BaseResponse.success(rawResponse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
