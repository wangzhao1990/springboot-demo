package com.zhao.demo.common.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * 微信配置类
 *
 * @author wangz
 * @create 2019/4/10
 */
@Data
@Component
public class WeCharProperties {

    @Value("${weixin.appid}")
    private String appId;

    @Value("${weixin.secret}")
    private String secret;

    @Value("${weixin.authorize.url}")
    private String authorizeURL;

    @Value("${weixin.access_token.url}")
    private String accessTokenURL;

    @Value("${weixin.user_info.url}")
    private String userInfoURL;

    @Value("${weixin.qrcode.url}")
    private String qrCodeURL;

    @Value("${weixin.redirect.url}")
    private String redirectURL;

}
