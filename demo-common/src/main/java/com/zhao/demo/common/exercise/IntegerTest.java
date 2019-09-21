package com.zhao.demo.common.exercise;

/**
 * TODO: 请添加描述
 *
 * @author wangzhao
 * @create 2019/9/2
 */
public class IntegerTest {

    public static void main(String[] args) {
        int m = Integer.MAX_VALUE;
        System.out.println(Integer.toBinaryString(m));
        int n = m + 1;
        System.out.println(n);
        System.out.println(Integer.toBinaryString(n));
        int a = 1;
        System.out.println(Integer.toBinaryString(a));
        int i = -1;
        System.out.println(Integer.toBinaryString(i));
        int b = i + 1;
        System.out.println(Integer.toBinaryString(b));
        int c = i >> 1;
        System.out.println(Integer.toBinaryString(c));
        int d = a >> 2;
        System.out.println(Integer.toBinaryString(d));
    }
}
