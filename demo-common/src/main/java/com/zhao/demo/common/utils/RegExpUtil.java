package com.zhao.demo.common.utils;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 */
public class RegExpUtil {

    public static void main(String[] args) {
        String url = "https://www.baidu.com/xiaohai/download/xxxxxx.pdf";
        System.out.println(url.matches("^https://.*\\.pdf$"));
//        testOne(url);
//        testTwo(url);
        testThree(url);

    }

    private static void testThree(String url) {
        String regex = "^(https://)(.*)(\\.pdf)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            MatchResult matchResult = matcher.toMatchResult();
            System.out.println(matchResult.group());
            System.out.println(matchResult.group(0));
            System.out.println(matchResult.group(1));
            System.out.println(matchResult.group(2));
            System.out.println(matchResult.group(3));
        }
    }

    private static void testTwo(String url) {
        String regex = "/";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        int i = 0;
        while (matcher.find()) {
            System.out.println(++i);
            System.out.println("start:" + matcher.start());
            System.out.println("end" + matcher.end());
        }
    }

    private static void testOne(String url) {
        String newUrl = url.replaceAll("^https:", "http:");
        System.out.println(newUrl);
    }
}
