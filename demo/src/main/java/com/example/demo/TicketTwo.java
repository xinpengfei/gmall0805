package com.example.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//进程 操作 资源类
/*juc 是什么 并发工具包  lock  和 synchronized  有什么区别  befer after   :表锁  行锁
线程  和进程
并发  并行
lambda  表达式
学习法  ;是什么  能干啥  在哪下  怎么用
常见的五种异常： 运行异常1.空指针异常  2. 类型装换异常  3.数组下越界 4.算数异常
                编译时异常 1.io操作异常 2.文件找不到异常 3.类型找不到异常 4. 没有对应的方法 5.已到达文件末尾异常
                错误 1.堆内存溢出  2. 栈内存溢出
 */
public class TicketTwo {

    public static void main(String[] args) {
        TicketRecose tR = new TicketRecose();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {
                    tR.slave();
                }

            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 35; i++) {
                tR.slave();
            }
        }).start();
        //继承？？？？？？？？？？
        new Thread(new Thread() {
            @Override
            public void run() {
                tR.slave();
            }
        }

        ).start();
    }

}

//创建一个资源类 对
class TicketRecose {
    private Lock lock = new ReentrantLock();
    private int number = 30;

    public void slave() {

        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "买了多少张票：" + (number--) + "还剩多少张：" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}