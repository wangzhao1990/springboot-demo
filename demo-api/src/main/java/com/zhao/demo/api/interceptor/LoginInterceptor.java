/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.zhao.demo.api.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zhao.demo.common.response.ResponseVo;
import com.zhao.demo.common.utils.redis.RedisHelper;
import com.zhao.demo.service.login.biz.LoginBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登陆拦截器
 *
 * @author lixiang
 * @date 2018/12/5
 * @since 1.0.0
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    private RedisHelper redisHelper;

    @Resource
    private LoginBiz loginBiz;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) {

        // 1.当请求的URL是获取真实token时,表示在走登入接口,则不需要做登入校验
        String requestUrl = httpServletRequest.getRequestURL().toString();
        log.info("请求的URL:{}", requestUrl);
        String URL = "/login/getToken";
        String loginUrl = "/login/getLoginUrl";
        if (requestUrl.contains(URL) || requestUrl.contains(loginUrl)) {
            return true;
        }

        // 2.查询sessionInfo在缓存中是否过期并查询权限系统token是否失效
        Cookie[] cookies = httpServletRequest.getCookies();
        log.info("cookies={}", JSONObject.toJSONString(cookies));
        ServletOutputStream writer;
        try {
            writer = httpServletResponse.getOutputStream();
            if (cookies == null || cookies.length == 0) {
                log.info("cookie为空");
                writer.print(JSONObject.toJSONString(ResponseVo.errorLoginReturn()));
                writer.flush();
                return false;
            }
            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase("token")) {
                    httpServletResponse.setCharacterEncoding("utf-8");
                    httpServletResponse.setContentType("application/json; charset=utf-8");
                    if (StringUtils.isEmpty(cookie.getValue())) {
                        log.info("token为空");
                        writer.print(JSONObject.toJSONString(ResponseVo.errorLoginReturn()));
                        writer.flush();
                        return false;
                    }
//                    SessionInfo sessionInfo = (SessionInfo) redisHelper.get(cookie.getValue());
//                    if (sessionInfo == null) {
//                        log.info("redis未查询到用户登录信息：{}", cookie.getValue());
//                        writer.print(JSONObject.toJSONString(ResponseVo.errorLoginReturn()));
//                        writer.flush();
//                        return false;
//                    }
//                    log.info("redis查询到用户登录信息：{}", JSONObject.toJSONString(sessionInfo));
//                    Response response = loginBiz.getLoginInfoByToken(sessionInfo.getToken());
//                    if (response.getStatus() == ResultEnum.SUCCESS.getCode()) {
//                        log.info("token有效");
//                        httpServletRequest.setAttribute("sessionInfo", sessionInfo);
//                        return true;
//                    } else {
//                        log.info("token失效");
//                        writer.print(JSONObject.toJSONString(ResponseVo.errorLoginReturn()));
//                        writer.flush();
//                        return false;
//                    }
                }
            }
            // cookie中没有token时
            log.info("cookie中没有token");
            writer.print(JSONObject.toJSONString(ResponseVo.errorLoginReturn()));
            writer.flush();
            return false;
        } catch (Exception e) {
            log.error("登录异常", e);
            throw new RuntimeException("登录异常");
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }
}
