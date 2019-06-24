package com.zhao.demo.common.utils.json;

import lombok.Data;

import java.util.Map;

/**
 * 
* @ClassName: JsonReturnFromData 
* @Description: 数据中心返回结果（芝麻授信时用到）
* @author ex-WANGZHAO926 
* @date 2017年5月24日 下午4:07:23
 */
@Data
public class JsonReturnFromData {

    private int status = 0;// 0=成功，1=失败
    
    private String errorCode;
    
    private String msg = "success";
    
    private Map<String, Object> body;
    
    private String requestId;
}
