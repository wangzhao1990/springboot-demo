package com.zhao.demo.common.exceptions;

/**
 * 登录异常
 *
 * @author wangz
 * @create 2019/4/10
 */
public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }
}
