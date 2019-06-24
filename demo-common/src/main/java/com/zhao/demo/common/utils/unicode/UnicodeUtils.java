package com.zhao.demo.common.utils.unicode;

import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: UnicodeUtils
 * @Description: Unicode编解码工具类
 * @author LIUYUEFENG559
 * @date 2016年5月16日 下午5:44:13
 */
public class UnicodeUtils {


    /**
     * @Title: decodeUnicode
     * @Description: 解码Unicode，即\\uXXXX
     * @param str
     * @return
     * @throws
     */
    public static String decodeUnicode(final String str) {
        if( StringUtils.isNotBlank(str) ) {
            final Charset set = Charset.forName("UTF-16");
            final Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
            final Matcher m = p.matcher(str);
            int start = 0;
            int start2 = 0;
            final StringBuffer sb = new StringBuffer();
            while (m.find(start)) {
                start2 = m.start();
                if (start2 > start) {
                    final String seg = str.substring(start, start2);
                    sb.append(seg);
                }
                final String code = m.group(1);
                final int i = Integer.valueOf(code, 16);
                final byte[] bb = new byte[4];
                bb[0] = (byte) ((i >> 8) & 0xFF);
                bb[1] = (byte) (i & 0xFF);
                final ByteBuffer b = ByteBuffer.wrap(bb);
                sb.append(String.valueOf(set.decode(b)).trim());
                start = m.end();
            }
            start2 = str.length();
            if (start2 > start) {
                final String seg = str.substring(start, start2);
                sb.append(seg);
            }
            return sb.toString();
        }
        return str;
    }
    
    /**
     * 
    * @Title: encodeUnicode 
    * @Description: 当字符串中有中文，将中文unicode转码 
    * @param str
    * @return
    * @throws
     */
    public static String encodeUnicode(String str) {
    	String result = "";
		if (StringUtils.isNotBlank(str)) {
			for (int i = 0; i < str.length(); i++) {
				int chr1 = (char) str.charAt(i);
				Character ch = new Character(str.charAt(i));
				if ((chr1 >= 19968 && chr1 <= 171941) || isChinese(str.charAt(i)) || chr1 == 183) {// 汉字范围
					String hex =Integer.toHexString(chr1);
					if (hex.length() <= 2) {
						result += "\\u00" + hex;
					} else {
						result += "\\u" + hex;
					}
				} else {
					result += convertStrForSpecialCharacter(str.charAt(i));
				}
			}
		}
		return result;
    }
    /** 
     * 判断是否为中文字符 
     * @param c 
     * @return 
     */  
    public static boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }
    
    /**
     * 
    * @Title: convertStrForSpecialCharacter 
    * @Description: 做特殊字符转义
    * @param c
    * @return
    * @throws
     */
    private static String convertStrForSpecialCharacter(char c) {
        String returnStr  = c+"";
        switch(c){
        case '"':
            returnStr = "\\"+returnStr;
            break;
        case '\\':
            returnStr = "\\"+returnStr;
            break;
        case '/':
            returnStr = "\\"+returnStr;
            break;
        case '\r':
            returnStr = "\\r";
            break;
        case '\b':
            returnStr = "\\\\b";
            break;
        case '\f':
            returnStr = "\\f";
            break;
        case '\n':
            returnStr = "\\n";
            break;
        case '\t':
            returnStr = "\\t";
            break;
        default:
            break;
        }
        return returnStr;
    }
    public static void main(String[] args) {
    	String str="你/是谁。我\\是谁，他\"又是谁！>\r\b\f\n\t";
		System.out.println(encodeUnicode(str));
	}
}
