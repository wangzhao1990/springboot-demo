package com.zhao.demo.common.utils.json;

/**
 * 限定返回格式类
 * 
 * @ClassName: JsonRetrun
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author EX-HUYAOYUAN700
 * @date 2016年4月21日 上午9:29:46
 */
public class JsonReturn {

    private String msg = "success";

    private Integer code = 1;

    private Object data;
    
    private Object params;

    public JsonReturn() {

    }

    public JsonReturn(String msg) {
        this.msg = msg;
    }

    public JsonReturn(Integer code) {
        this.code = code;
    }

    public JsonReturn(String msg, Object data) {
        this.data = data;
        this.msg = msg;
    }

    public JsonReturn(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }
    
    public JsonReturn(Integer code, Object data) {
        this.data = data;
        this.code = code;
    }

    public JsonReturn(Integer code, String msg, Object data) {
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

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "JsonReturn [msg=" + msg + ", code=" + code + ", data=" + data
                + ", params=" + params + "]";
    }
}
