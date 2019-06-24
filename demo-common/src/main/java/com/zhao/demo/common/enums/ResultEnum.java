/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.zhao.demo.common.enums;

import lombok.Getter;

/**
 * TODO: 请添加描述
 *
 * @author lixiang
 * @date 2018/12/5
 * @since 1.0.0
 */
public enum ResultEnum {
    SYSTEM_ERROR(-1, "系统内部错误"),
    FAILED(0, "失败"),
    SUCCESS(1, "成功"),
    NO_DATA(2, "查无数据"),
    SYSTEM_BUSY(3,"系统繁忙");

    @Getter
    private Integer code;

    @Getter
    private String name;

    ResultEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }


}
