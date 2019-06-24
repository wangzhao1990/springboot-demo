package com.zhao.demo.common.enums;

import lombok.Getter;

/**
 * 操作返回
 *
 * @author ex-WANGZHAO926
 * @date 2018-12-19
 * @since 1.0.0
 */
public enum  ResponseMessageEnum {

    SAVE_SUCCESS("保存成功"),
    SUBMIT("已提交"),
    AUDIT_DONE("已完成审核"),;

    @Getter
    private String name;

    ResponseMessageEnum(String name) {
        this.name = name;
    }
}
