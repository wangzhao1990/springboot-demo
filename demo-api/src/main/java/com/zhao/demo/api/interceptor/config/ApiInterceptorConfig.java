/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.zhao.demo.api.interceptor.config;

import com.zhao.demo.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * 拦截器注册
 *
 * @author lixiang
 * @date 2018/12/5
 * @since 1.0.0
 */
@Configuration
public class ApiInterceptorConfig extends WebMvcConfigurerAdapter {

//    @Resource
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
}
