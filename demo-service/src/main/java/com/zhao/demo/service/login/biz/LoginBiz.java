package com.zhao.demo.service.login.biz;

/**
 * 登录业务类
 *
 * @author wangz
 * @create 2019/4/10
 */
public interface LoginBiz {

    /**
     * 获取微信登录二维码
     */
    void getWeCharLoginQRCode();

    /**
     * 微信登录确认
     */
    void weCharLoginConfirm();
}
