package com.zhao.demo.api.controller.login;

import com.zhao.demo.service.login.biz.LoginBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 微信登录处理类
 *
 * @author wangz
 * @create 2019/4/10
 */
@RestController
@RequestMapping("/weChar")
@Api(tags = "微信登录处理类")
public class WeCharLoginController {

    @Resource
    private LoginBiz loginBiz;

    @RequestMapping(value = "/login/qrcode",method = RequestMethod.GET)
    @ApiOperation(value = "获取登录二维码",notes = "后端生成微信登录二维码")
    public void getWeCharLoginQRCode(){
        loginBiz.getWeCharLoginQRCode();
    }

    @RequestMapping(value = "/login/confirm",method = RequestMethod.GET)
    @ApiOperation(value = "登录确认",notes = "微信扫码成功后回调接口")
    public void weCharLoginConfirm(){
        loginBiz.weCharLoginConfirm();
    }
}
