package com.liuning.invoke.invoker;

/**
 * 通用返回
 *
 * @author liuning
 * @since 2020-09-26 21:25
 */
public class BaseResponse<T> {

    public static BaseResponse<?> EMPTY = new BaseResponse<>();

    static {
        EMPTY.setSuccess(true);
        EMPTY.setCode("000000");
        EMPTY.setMessage("请求成功");
    }

    /**
     * 调用成功标识
     */
    boolean success;

    /**
     * 返回码值
     */
    private String code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 原始返回
     */
    T result;

    public static<T> BaseResponse<T> empty() {
        @SuppressWarnings("unchecked")
        BaseResponse<T> empty = (BaseResponse<T>) EMPTY;
        return empty;
    }

    public static<T> BaseResponse<T> success(T result) {
        if (result == null) {
            return empty();
        }
        BaseResponse<T> response = new BaseResponse<>();
        response.setSuccess(true);
        response.setCode("000000");
        response.setMessage("请求成功");
        response.setResult(result);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "success=" + success +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
