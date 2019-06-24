package com.zhao.demo.common.exercise.arithmetic;

/**
 * 斐波那契数
 * 1.纯递归算法
 * 2.动态规划算法
 *
 * @author wangzhao
 * @create 2019/5/6
 */
public class Test {


    private static int test1 (int n) {

        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }
        return test1(n - 1) + test1(n - 2);
    }

    private static long f (long n) {
        if (n <=2) {
            return 1;
        }
        long n_1 = 1;
        long n_2 = 1;
        long answer = 0;
        for (long i = 3; i < n + 1; i++) {
            answer = n_1 + n_2;
            n_2 = n_1;
            n_1 = answer;
        }
        return answer;
    }

    public static void main(String[] args) {


        for (long i = 1; i < 50; i++) {
            System.out.println(f(i));
        }

    }


}
