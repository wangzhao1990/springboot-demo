/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.zhao.demo.api.interceptor.config;

import com.zhao.demo.api.interceptor.AuthenticationInterceptor;
import com.zhao.demo.api.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
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

    @Resource
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration registration = registry.addInterceptor(authenticationInterceptor);     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");                    //所有路径都被拦截
//        registration.excludePathPatterns("/","/login","/error","/static/**","/logout");       //添加不拦截路径
    }

//    @Resource
//    private LoginInterceptor loginInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
}
