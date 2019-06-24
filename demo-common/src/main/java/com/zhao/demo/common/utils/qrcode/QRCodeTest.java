package com.zhao.demo.common.utils.qrcode;

/**
 * 二维码测试类
 *
 * @author wangz
 * @create 2019/4/9
 */
public class QRCodeTest {

    public static void main(String[] args) throws Exception {
        String text = "http://www.benxiaohai001.cn/data/";
        QRCodeUtil.encode(text, "", "C:\\Users\\wangz\\Desktop", true);
    }
}
