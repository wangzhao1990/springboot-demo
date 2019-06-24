package com.zhao.demo.common.exercise;

/**
 * TODO: 请添加描述
 *
 * @author wangz
 * @create 2019/4/3
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {
        ThreadFive threadFive = new ThreadFive();
        ThreadSix threadSix = new ThreadSix(threadFive);
        threadFive.start();
        threadSix.start();
        Thread.sleep(1000l);
        System.out.println(threadSix.getName() + "：状态：" +threadSix.getState());
    }
}
