package com.zhao.demo.common.exercise.sys;

import com.alibaba.fastjson.JSONObject;

import java.util.Properties;

/**
 * TODO: 请添加描述
 *
 * @author wangzhao
 * @create 2019/4/29
 */
public class SystemTest {

    public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(JSONObject.toJSONString(properties));
    }
}
