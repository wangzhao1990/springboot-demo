package com.zhao.demo.common.utils.json;

/**
 * 房行自定义异常类
* @ClassName: HouseAgencyException 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author EX-MAOJUN600 
* @date 2016年11月28日 下午5:13:47
 */
public class HouseAgencyException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg = "error";

    private Integer code = 0;

    private Object data;

    public HouseAgencyException() {

    }

    public HouseAgencyException(String msg) {
        this.msg = msg;
    }

    public HouseAgencyException(Integer code) {
        this.code = code;
    }

    public HouseAgencyException(String msg, Object data) {
        this.data = data;
        this.msg = msg;
    }

    public HouseAgencyException(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }
    
    public HouseAgencyException(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public HouseAgencyException(Integer code, String msg, Object data) {
        this.data = data;
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
