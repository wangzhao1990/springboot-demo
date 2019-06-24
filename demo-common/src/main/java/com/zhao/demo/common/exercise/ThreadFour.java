package com.zhao.demo.common.exercise;

/**
 * 线程测试类
 *
 * @author wangz
 * @create 2019/4/2
 */
public class ThreadFour extends Thread {

    //    private volatile boolean isRunning = true;
    private boolean isRunning = true;

    public void setRunning(boolean running) {
        isRunning = running;
    }


    @Override
    public void run() {
        System.out.println("进入run()方法");
        System.out.println("当前线程状态：" + getState());
        int i = 0;
        while (isRunning) {
            i++;
//            try {
//                Thread.sleep(200l);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("循环中");
        }
        System.out.println("线程被停止了");
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadFour threadFour = new ThreadFour();
        threadFour.start();
        Thread.sleep(1000l);
        threadFour.setRunning(false);
        System.out.println(Thread.currentThread().getName()+":结束");
    }
}
