package com.zhao.demo.common.exercise;

/**
 * 线程测试类
 *
 * @author wangz
 * @create 2019/4/2
 */
public class ThreadFive extends Thread {

    @Override
    public void run() {
        System.out.println(getName() + "进入run()方法");
        synchronized (this) {
            try {
                System.out.println(getName() + "睡眠5秒");
                Thread.sleep(5000l);
                System.out.println(getName() + "睡眠结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + "线程被停止了");
    }
}
