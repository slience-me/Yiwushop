package xyz.slienceme.project_shop.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author slience_me
 * @Time : 2021/7/12  14:17
 */
//保证序列化json，如果是null的对象,该对象不参加序列化
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private final int status;//响应码
    private String msg;//提示信息
    private T data;//泛型数据

    //有响应码 可以无提示信息和数据
    private Result(int status) {
        this.status = status;
    }

    //有响应码和数据，可以没有提示信息
    private Result(int status, T data) {
        this.status = status;
        this.data = data;
    }

    //有响应码和提示信息，可以没有数据
    private Result(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    //有响应码，提示信息和数据
    public Result(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 只包含响应码创建
     */
    public static <T> Result<T> createBySuccess() {
        return new Result<>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 包含响应码和提示信息创建
     */
    public static <T> Result<T> createBySuccessMessage(String msg) {
        return new Result<>(ResponseCode.SUCCESS.getCode(), msg);
    }

    /**
     * 包含响应码和数据创建
     */
    public static <T> Result<T> createBySuccess(T data) {
        return new Result<>(ResponseCode.SUCCESS.getCode(), data);
    }

    /**
     *
     */
    public static <T> Result<T> createBySuccess(String msg, T data) {
        return new Result<>(ResponseCode.SUCCESS.getCode(), msg, data);
    }

    //使用静态方法开放
    //成功响应创建-------------------------------------------------------以下

    /**
     * 包含响应码和提示信息
     */
    public static <T> Result<T> createByError() {
        return new Result<>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
    }

    /**
     * 包含响应码和数据
     */
    public static <T> Result<T> createByErrorData(T data) {
        return new Result<>(ResponseCode.ERROR.getCode(), data);
    }

    /**
     * 包含响应码和数据和提示信息
     */
    public static <T> Result<T> createByErrorDataAndMsg(T data, String errorMessage) {
        return new Result<>(ResponseCode.ERROR.getCode(), errorMessage, data);
    }

    /**
     * 包含响应码和提示信息
     */
    public static <T> Result<T> createByErrorMessage(String errorMessage) {
        return new Result<>(ResponseCode.ERROR.getCode(), errorMessage);
    }

    @JsonIgnore //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseCode.SUCCESS.getCode();
    }
    //成功响应创建--------------------------------------------------------以上

    //失败响应创建---------------------------------------------------------以下

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
    //失败响应创建--------------------------------------------------------以上

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
