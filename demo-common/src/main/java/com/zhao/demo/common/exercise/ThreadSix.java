package com.zhao.demo.common.exercise;

/**
 * 线程测试类
 *
 * @author wangz
 * @create 2019/4/2
 */
public class ThreadSix extends Thread {

    private ThreadFive threadFive;

    ThreadSix (ThreadFive threadFive) {
        this.threadFive = threadFive;
    }

    @Override
    public void run() {
        System.out.println(getName() + "进入run()方法");
        System.out.println(getName() + "：状态：" + getState());
        synchronized (threadFive) {
            System.out.println(getName() + "执行同步代码块");
            System.out.println(getName() + "：状态：" + getState());

        }
        System.out.println(getName() + "线程被停止了");
    }
}
