package com.zhao.demo.api.filter;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * 去首尾空格、敏感词过滤、xss攻击过滤
 * @author zlp
 *
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String , String[]> params = new HashMap<String, String[]>();      
    
    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似      
        super(request);     
        //将参数表，赋予给当前的Map以便于持有request中的参数      
        Map<String, String[]> requestMap=request.getParameterMap();  
        //System.out.println("转化前参数："+JSON.toJSONString(requestMap));  
        this.params.putAll(requestMap);  
        this.modifyParameterValues();  
        //System.out.println("转化后参数："+JSON.toJSONString(params));  
    }      
    /** 
     * 重写getInputStream方法  post类型的请求参数必须通过流才能获取到值 
     */  
//    @Override
//    public ServletInputStream getInputStream() throws IOException {
//          //非json类型，直接返回
//          if(!(super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)||super.getHeader(HttpHeaders.CONTENT_TYPE).equalsIgnoreCase("application/json; charset=utf-8"))){
//              return super.getInputStream();
//          }
//          //为空，直接返回
//          String json = IOUtils.toString(super.getInputStream(), "utf-8");
//          if (StringUtils.isEmpty(json)) {
//              return super.getInputStream();
//          }
//          //System.out.println("转化前参数："+json);
//
//          SensitiveWord sw = new SensitiveWord("CensorWords.txt");
//  	      sw.InitializationWork();
//  	      json = sw.filterInfo(stripXSS(json));
//          Map<String,Object> map=JUtil.toMap(json);
//          //将header 参数放入body
//          if(super.getHeader("token")!=null) map.put("token", super.getHeader("token"));
//          if(super.getHeader("userGuid")!=null) map.put("userGuid", super.getHeader("userGuid"));
//          if(super.getHeader("modelType")!=null) map.put("modelType", super.getHeader("modelType"));//设备类型：1-ios设备；2-Android设备
//          if(super.getHeader("appVersion")!=null) map.put("appVersion", super.getHeader("appVersion"));//app 版本号
//          //System.out.println("转化后参数："+JSON.toJSONString(map));
//          ByteArrayInputStream bis = new ByteArrayInputStream(JSON.toJSONString(map).getBytes("utf-8"));
//          return new MyServletInputStream(bis);
//    }
    /** 
     * 将parameter的值去除空格后重写回去  
     */  
    public void modifyParameterValues(){  
        Set<String> set =params.keySet();      
        Iterator<String> it=set.iterator();      
        while(it.hasNext()){      
           String key= (String) it.next();      
           String[] values = params.get(key);      
           values[0] = stripXSS(values[0].trim());      
           params.put(key, values);      
         }      
    }      
    /** 
     * 重写getParameter 参数从当前类中的map获取   
     */  
    @Override      
    public String getParameter(String name) {  
        String[]values = params.get(name);      
        if(values == null || values.length == 0) {      
            return null;      
        }      
        return values[0];      
    }    
    /** 
     * 重写getParameterValues 
     */  
    public String[] getParameterValues(String name) {//同上      
         return params.get(name);      
    }  
      
    class MyServletInputStream extends ServletInputStream {
      private ByteArrayInputStream bis;  
      public MyServletInputStream(ByteArrayInputStream bis){  
          this.bis=bis;  
      }  
      @Override  
      public boolean isFinished() {  
          return true;  
      }  

      @Override  
      public boolean isReady() {  
          return true;  
      }  

      @Override  
      public void setReadListener(ReadListener listener) {
            
      }  
      @Override  
      public int read() throws IOException {  
           return bis.read();  
      }  
    }  
    private String stripXSS(String value) {
		if (value != null) {
			// Avoid anything between script tags
			Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid anything in a
			// e­xpression
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome </script> tag
			scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Remove any lonesome <script ...> tag
			scriptPattern = Pattern.compile("<script(.*?)>",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid eval(...) e­xpressions
			scriptPattern = Pattern.compile("eval\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid e­xpression(...) e­xpressions
			scriptPattern = Pattern.compile("e­xpression\\((.*?)\\)",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid javascript:... e­xpressions
			scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid vbscript:... e­xpressions
			scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
			value = scriptPattern.matcher(value).replaceAll("");
			// Avoid onload= e­xpressions
			scriptPattern = Pattern.compile("onload(.*?)=",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
			value = scriptPattern.matcher(value).replaceAll("");
 
		}
		return value;
	}

}
