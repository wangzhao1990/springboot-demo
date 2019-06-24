package com.zhao.demo.common.response;


import lombok.Builder;
import lombok.Data;

/**
 * 统一返回对象
 */
@Data
@Builder
public class ResponseVo {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回内容
     */
    private Object data;

    /**
     * 获取错误返回对象
     * @param errorCode 错误码
     * @param message 错误信息
     * @return 返回结果
     */
    public static ResponseVo errorResponse(String errorCode,String message){
        return ResponseVo.builder().success(false).errorCode(errorCode).message(message).build();
    }

    /**
     * 获取错误返回对象
     * @param message 错误信息
     * @return 返回结果
     */
    public static ResponseVo errorResponse(String message){
        return ResponseVo.errorResponse(null,message);
    }

    /**
     * 获取错误返回对象
     * @param message 信息
     * @param data 数据
     * @return 返回结果
     */
    public static ResponseVo successResponse(String message,Object data){
        return ResponseVo.builder().success(true).message(message).data(data).build();
    }

    /**
     * 获取错误返回对象
     * @param data 数据
     * @return 返回结果
     */
    public static ResponseVo successResponse(Object data){
        return ResponseVo.successResponse(null,data);
    }

    /**
     * 登录异常
     * @return 返回结果
     */
    public static ResponseVo errorLoginReturn(){
        return errorResponse("1001","Not logged in");
    }


}
