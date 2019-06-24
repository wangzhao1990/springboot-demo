/*
 * Copyright (C) 2018 Pingan, Inc. All Rights Reserved.
 */
package com.zhao.demo.api.interceptor;


import com.alibaba.fastjson.JSONObject;
import com.zhao.demo.common.exceptions.ApiException;
import com.zhao.demo.common.exceptions.LoginException;
import com.zhao.demo.common.response.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * WebExceptionHandler
 */
@Slf4j
@ControllerAdvice
public class WebExceptionHandler {

    /**
     * 登录异常处理
     *
     * @param ex LoginException
     * @return ResponseVo
     */
    @ExceptionHandler(value = LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseVo handleLoginException(LoginException ex) {
        log.info("handleLoginException ----------exception:{}", ex.getMessage());
        return ResponseVo.errorLoginReturn();
    }


    /**
     * 请求参数校验错误封装(返回第一条错误信息)
     *
     * @param ex BindException
     * @return ResponseVo
     */
    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseVo handleBindException(BindException ex) {
        log.info("handleBindException ----------exception:{}", JSONObject.toJSONString(ex.getBindingResult()));
        return ResponseVo.errorResponse(ex.getAllErrors().get(0).getDefaultMessage());
    }

    /**
     * 参数验证失败
     *
     * @param ex MethodArgumentNotValidException
     * @return ResponseVo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseVo handleInvalidRequestError(MethodArgumentNotValidException ex) {
        log.info("handleInvalidRequestError ---------------");
        BindingResult bindingResult = ex.getBindingResult();
        String errorMesssage = bindingResult.getFieldErrors().get(0).getDefaultMessage();
        log.info("handleInvalidRequestError errorMesssage:" + errorMesssage);
        return ResponseVo.errorResponse(errorMesssage);
    }

    /**
     * 参数miss
     *
     * @param ex MissingServletRequestParameterException
     * @return ResponseVo
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public ResponseVo handleMissingServletRequestParameterError(MissingServletRequestParameterException ex) {
        log.info("handleInvalidRequestError ---------------");
        String errorMessage = ex.getMessage();
        return ResponseVo.errorResponse(errorMessage);
    }


    /**
     * ApiException捕获打印
     * @param e ApiException
     * @return ResponseVo
     */
    @ExceptionHandler(value = ApiException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseVo handleRuntimeException(ApiException e){
        log.info("handleRuntimeException 统一异常打印 {}", e);
        return ResponseVo.errorResponse(e.getMessage());
    }
    /**
     * Exception捕获打印
     * @param e Exception
     * @return ResponseVo
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseVo handleException(Exception e){
        log.info("handleException 统一异常打印 {}", e);
        return ResponseVo.errorResponse("服务器繁忙");
    }
}
