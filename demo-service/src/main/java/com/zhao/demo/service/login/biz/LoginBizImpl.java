package com.zhao.demo.service.login.biz;

import com.alibaba.fastjson.JSONObject;
import com.zhao.demo.common.exceptions.LoginException;
import com.zhao.demo.common.properties.WeCharProperties;
import com.zhao.demo.common.utils.qrcode.QRCodeUtil;
import com.zhao.demo.common.utils.request.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.logging.LogException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;

/**
 * TODO: 请添加描述
 *
 * @author wangz
 * @create 2019/4/10
 */
@Slf4j
@Service
public class LoginBizImpl implements LoginBiz {

    @Resource
    private WeCharProperties weCharProperties;

    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取微信登录二维码
     */
    @Override
    public void getWeCharLoginQRCode() {
        HttpServletResponse response = RequestUtil.getResponse();
        try {
            String qrcodeURL = MessageFormat.format(weCharProperties.getQrCodeURL(), URLEncoder.encode(weCharProperties.getRedirectURL(),"UTF-8"));
            response.sendRedirect(qrcodeURL);
        } catch (IOException e) {
            log.error("重定向二维码链接异常",e);
            throw new LogException("重定向二维码链接异常");
        }

    }

    /**
     * 微信登录确认
     */
    @Override
    public void weCharLoginConfirm() {
        HttpServletRequest request = RequestUtil.getRequest();
        String code = request.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            throw new LoginException("授权拒绝");
        }
        // 通过code获取access_token
        // https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        JSONObject accessResult = restTemplate.getForObject(weCharProperties.getAccessTokenURL(), JSONObject.class, code);
        log.info("access_token info :{}",accessResult.toJSONString());

        // 通过access_token获取用户信息
        JSONObject userInfoResult = restTemplate.getForObject(weCharProperties.getUserInfoURL(), JSONObject.class, accessResult.getString("access_token"), accessResult.getString("openid"));

        // 存储用户信息，设置登录状态，种cookie

        // 重定向到首页
        HttpServletResponse response = RequestUtil.getResponse();
        try {
            response.sendRedirect(weCharProperties.getRedirectURL());
        } catch (IOException e) {
            log.error("重定向首页失败",e);
            throw new LoginException("重定向首页失败");
        }
    }
}
