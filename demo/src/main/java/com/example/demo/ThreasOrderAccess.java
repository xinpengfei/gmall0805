package com.example.demo;


//A线程打印5次 ，B打印10，C打印15次依次循环10次

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShoreRescourse {
    private int flay = 1;
    private Lock lock = new ReentrantLock();

    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();


    public void printw5() {
        lock.lock();
        try {
            //判断
            while (flay != 1) {
                c1.await();
            }
            //干活
            for (int i = 0; i <5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flay=2;
            c2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printw10() {
        lock.lock();
        try {
            //判断
            while (flay != 2) {
            c2.await();
            }
            //干活
            for (int i = 0; i <10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flay=3;
            c3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printw15() {
        lock.lock();
        try {
            //判断
            while (flay != 3) {
            c3.await();
            }
            //干活
            for (int i = 0; i <15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            flay=1;
            c1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 哗啦圈
 *
 * @author:xpf
 * @date: 2019/12/16 19:21
 */
public class ThreasOrderAccess {
    public static void main(String[] args) {
        ShoreRescourse shoreRescourse = new ShoreRescourse();
        new Thread(() -> {
            for (int i = 0; i <10 ; i++) {
                shoreRescourse.printw5();
            }

        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    shoreRescourse.printw10();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <10 ; i++) {
                    shoreRescourse.printw15();
                }
            }
        }, "C").start();
    }
}
