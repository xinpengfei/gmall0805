package com.example.demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/* 高内聚 低耦合的前提下 ：线程 操作 资源类
    判断 干活  通知
    防止多线程通信时 虚假唤醒的bug 将if 还成 while（将里面的线程辣出去再进行判断）


 */

//判断 干活 通知
//资源类
class Close {
    private int numbers = 0;

//sycchronized 什么时候释放的？？？？？

    /**
     * 怎样获得锁  又是怎样释放 让别的线程 进入？？？
     * 然后  怎样  进去判断后等待？？
     * 何时释放锁  别的线程是什么时候获得锁？
     */
    public synchronized void slave() throws InterruptedException {
        //nunbers为1  该线程进入时就会等待
        //1.判断
        if (numbers != 0) {
            this.wait();
        }
        //2.干活
        ++numbers;
        System.out.println(Thread.currentThread().getName() + "\t" + numbers);
        //3.通知
        this.notifyAll();

    }

    public void update() {
        final Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        lock.lock();
        try {
            //1.判断
            if (numbers == 0) {
                condition.await();
            }
            //2.干活
            --numbers;
            System.out.println(Thread.currentThread().getName() + "\t" + numbers);
            //3.通知
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}

public class Day02 {
    public static void main(String[] args) {
        Close close = new Close();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        close.slave();
                    } catch (InterruptedException e) {

                    }
                }
            }
        }, "A").start();
        //用lambdab表达式  ：拷贝小括号  写死右箭头 落地大括号
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    close.update();
                    //为什么相同会报错？？？？？？？？？？？
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        close.slave();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "C").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        close.update();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "D").start();

    }
}

