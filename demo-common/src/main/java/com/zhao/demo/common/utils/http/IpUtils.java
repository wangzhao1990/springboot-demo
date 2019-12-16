package com.zhao.demo.common.utils.http;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 根据request获取ip 
 * @author luqiusheng
 *
 */
public class IpUtils {
	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取Proxy-Client-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("Proxy-Client-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("WL-Proxy-Client-IP");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
	
	
    /**
     * 获取真实IP地址
     * <p>使用getRealIP代替该方法</p>
     * @param request req
     * @return ip
     */
    @Deprecated
    public static String getClinetIpByReq(HttpServletRequest request) {
        // 获取客户端ip地址
        String clientIp = request.getHeader("x-forwarded-for");
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        /*
         * 对于获取到多ip的情况下，找到公网ip.
         */
        String sIP = null;
        if (clientIp != null && !clientIp.contains("unknown") && clientIp.indexOf(",") > 0) {
            String[] ipsz = clientIp.split(",");
            for (String anIpsz : ipsz) {
                if (!isInnerIP(anIpsz.trim())) {
                    sIP = anIpsz.trim();
                    break;
                }
            }
            /*
             * 如果多ip都是内网ip，则取第一个ip.
             */
            if (null == sIP) {
                sIP = ipsz[0].trim();
            }
            clientIp = sIP;
        }
        if (clientIp != null && clientIp.contains("unknown")){
            clientIp =clientIp.replaceAll("unknown,", "");
            clientIp = clientIp.trim();
        }
        if ("".equals(clientIp) || null == clientIp){
            clientIp = "127.0.0.1";
        }
        return clientIp;
    }
    
    /**
     * 判断IP是否是内网地址
     * @param ipAddress ip地址
     * @return 是否是内网地址
     */
    public static boolean isInnerIP(String ipAddress) {
        boolean isInnerIp;
        long ipNum = getIpNum(ipAddress);
        /**   
        私有IP：A类  10.0.0.0-10.255.255.255   
               B类  172.16.0.0-172.31.255.255   
               C类  192.168.0.0-192.168.255.255   
        当然，还有127这个网段是环回地址   
        **/
        long aBegin = getIpNum("10.0.0.0");
        long aEnd = getIpNum("10.255.255.255");
        
        long bBegin = getIpNum("172.16.0.0");
        long bEnd = getIpNum("172.31.255.255");
        
        long cBegin = getIpNum("192.168.0.0");
        long cEnd = getIpNum("192.168.255.255");
        isInnerIp = isInner(ipNum, aBegin, aEnd) || isInner(ipNum, bBegin, bEnd) || isInner(ipNum, cBegin, cEnd)
                || ipAddress.equals("127.0.0.1");
        return isInnerIp;
    }
    private static long getIpNum(String ipAddress) {
        String[] ip = ipAddress.split("\\.");
        long a = Integer.parseInt(ip[0]);
        long b = Integer.parseInt(ip[1]);
        long c = Integer.parseInt(ip[2]);
        long d = Integer.parseInt(ip[3]);
        return a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;
    }
    
    private static boolean isInner(long userIp, long begin, long end) {
        return (userIp >= begin) && (userIp <= end);
    }
    public static String getRealIP(HttpServletRequest request){
        // 获取客户端ip地址
        String clientIp = request.getHeader("x-forwarded-for");
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        String[] clientIps = clientIp.split(",");
        if(clientIps.length <= 1) return clientIp.trim();
        // 判断是否来自CDN
        if(isComefromCDN(request)){
            if(clientIps.length>=2) return clientIps[clientIps.length-2].trim();
        }
        return clientIps[clientIps.length-1].trim();
    }
    private static boolean isComefromCDN(HttpServletRequest request) {
        String host = request.getHeader("host");
        return host.contains("www.189.cn") ||host.contains("shouji.189.cn") || host.contains(
                "image2.chinatelecom-ec.com") || host.contains(
                "image1.chinatelecom-ec.com");
    }
}
