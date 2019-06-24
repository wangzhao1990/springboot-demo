
/** 
* @Title: CaReturnData.java 
* @Package com.pingan.haofang.goldcenter.common.json 
* @Description: TODO(用一句话描述该文件做什么) 
* @author ZOUHAIJUN575 
* @date 2017年8月24日 下午2:24:35 
* @version V1.0
*/  
package com.zhao.demo.common.utils.json;

import lombok.Data;

/** 
* @ClassName: CaReturnData 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author ZOUHAIJUN575 
* @date 2017年8月24日 下午2:24:35  
*/
@Data
public class CaReturnData<T> {

    
    /** 
    * @Fields status :是否成功。0成功，1失败
    */
    private Integer status;
    
    /** 
    * @Fields code : 相应的错误信息代码
    * 0：业务执行成功
    *-1：业务执行失败
    *1：创建用户，但用户已存在
    *2：短信验证码错误
    *3：短信验证码已过期
    */
    private Integer code;
    
    /** 
    * @Fields msg :相应描述
    */
    private String msg;
    
    /** 
    * @Fields result :详细信息
    */
    private T result;
}
