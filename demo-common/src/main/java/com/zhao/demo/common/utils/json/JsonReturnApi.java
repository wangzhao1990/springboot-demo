package com.zhao.demo.common.utils.json;

import lombok.Data;

/**
 * 
* @ClassName: JsonReturnApi 
* @Description: 带泛型的api返回结果
* @author ex-gongdaoshun700 
* @date 2017年7月13日 上午11:08:15 
* @param <T>
 */
@Data
public class JsonReturnApi<T> {

    /**
     * @Fields status : 状态值，1表示成功
     */
    private int status = 1; // 默认为1

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
    private T result;
}
