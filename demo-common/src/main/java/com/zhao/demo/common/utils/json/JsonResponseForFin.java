/**
 * 
 * @Title: JsonResponseForFin.java
 * @Description:TODO
 * @Author GAOPENGFEI854
 * @Date 2017年3月13日
 * @version
 */
package com.zhao.demo.common.utils.json;

import lombok.Data;

/**
 * 
 * @ClassName :JsonResponseForFin
 * @Description:TODO
 * @Author GAOPENGFEI854
 * @Date 2017年3月13日
 * @version
 */
@Data
public class JsonResponseForFin {
	 /**
     * @Fields status : 状态值，1表示成功
     */
    private Integer status = 1; // 默认为1
    
    private String errno;
    
    private String errmsg;  
    
    private Object data;  
}
