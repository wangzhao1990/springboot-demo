package com.zhao.demo.common.exercise;

/**
 * 线程练习类
 */
public class ThreadExe {

    public static void main(String[] args) {

        creat();
//        testSleep();
    }

    private static void testSleep() {
        Thread testSleep = new Thread (() -> {
            System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
            try {
                Thread.sleep(1000L);
                System.out.println(Thread.currentThread().getName() + ":" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        testSleep.start();
    }

    private static void creat() {
        System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().isAlive());
        System.out.println("===============================");
        ThreadOne threadOne = new ThreadOne();
        System.out.println(Thread.currentThread().getName() + ":" + threadOne.getName());
        System.out.println(Thread.currentThread().getName() + ":" + threadOne.isAlive());
        System.out.println(Thread.currentThread().getName() + ":" + threadOne.getId());
        threadOne.start();
        System.out.println(Thread.currentThread().getName() + ":" + threadOne.isAlive());
        System.out.println("===============================");
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ":" + this.getClass().getName());
            }
        });
        // 纳姆达表达式中的变量this无法引用
//        Thread threadTwo = new Thread(() -> System.out.println(this.getClass().getName()));
//        Thread threadTwo = new Thread(() -> System.out.println("run()"));
        System.out.println(Thread.currentThread().getName() + ":" + threadTwo.getName());
        System.out.println(Thread.currentThread().getName() + ":" + threadTwo.isAlive());
        threadTwo.start();
        System.out.println(Thread.currentThread().getName() + ":" + threadTwo.isAlive());
        System.out.println("===============================");
        // 内部类的使用
        ThreadOne.ThreadTwo threadTwo1 = threadOne.new ThreadTwo();
        new Thread(threadTwo1).start();
        ThreadOne.ThreadThree three = new ThreadOne.ThreadThree();
        new Thread(three).start();
    }

}
