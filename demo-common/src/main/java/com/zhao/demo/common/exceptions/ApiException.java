package com.zhao.demo.common.exceptions;

/**
 * 自定义API异常
 *
 * @author wangz
 * @create 2019/3/26
 */
public class ApiException extends RuntimeException {

    public ApiException(String message){
        super(message);
    }
}
