package com.example.learnmall.common.api;

/**
 * @Author liaozhenglong
 * @Date 2019/11/20 9:59
 * 通用返回对象
 **/
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    protected CommonResult(){

    }

    protected CommonResult(long code,String message,T data){
        this.code=code;
        this.message=message;
        this.data=data;
    }

    /**
     * 成功是返回的结果
     * @param data 返回的数据*/
    public static <T>CommonResult<T> success(T data){
        return  new CommonResult<T>(ResultCode.SUCCESS.getCode(),ResultCode.SUCCESS.getMessage(),data);
    }

    /**
     * 成功返回的结果
     * @param data 返回的数据
     * @param message 提示的信息*/
    public static <T>CommonResult<T> success(T data,String message){
        return  new CommonResult<>(ResultCode.SUCCESS.getCode(),message,data);
    }

    /**
     * 失败返回的结果
     * @param  iErrorCode 错误码*/
    public static <T>CommonResult<T> failed(IErrorCode iErrorCode){
        return new CommonResult<>(iErrorCode.getCode(),iErrorCode.getMessage(),null);
    }
    /**
     * 失败返回的结果
     * @param iErrorCode 错误码
     * @param message 提示信息*/
    public static <T>CommonResult<T> failed(IErrorCode iErrorCode,String message){
        return new CommonResult<>(iErrorCode.getCode(),message,null);
    }

    public static <T>CommonResult<T>failed(){
        return failed(ResultCode.FAILED);
    }
    public static <T>CommonResult<T>failed(String message){
        return failed(ResultCode.FAILED,message);
    }

    /**
     * 参数验证失败*/
    public static <T>CommonResult<T> validateFailed(){
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败
     * @param message 提示信息*/
    public static <T>CommonResult<T>validateFailed(String message){
        return new CommonResult<>(ResultCode.VALIDATE_FAILED.getCode(),message,null);
    }

    /**
     * 为登入返回的结果*/
    public static <T>CommonResult unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage(),data);
    }

    /**
     * 未授权返回的结果*/
    public static <T>CommonResult<T> forbidden(T data){
        return new CommonResult<>(ResultCode.FORBIDDEN.getCode(),ResultCode.FORBIDDEN.getMessage(),data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
