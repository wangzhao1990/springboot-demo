package com.zhao.demo.common.utils.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 接口统一返回
 * @author linghaijin781
 * @date 2017/12/19 10:56
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpResult implements Serializable {
    /**
     * @Fields status : 状态值，1表示成功
     * 默认为1
     */
    private int status = 1;

    /**
     * @Fields errorCode : 错误码。
     */
    private String errorCode;

    /**
     * @Fields msg : 错误信息
     */
    private String msg = "success";

    /**
     * @Fields requestId : 原请求id，错误时设置
     */
    private String requestId;

    /**
     * @Fields result : 具体结果。失败时可不设置
     */
    private Object result;
}
