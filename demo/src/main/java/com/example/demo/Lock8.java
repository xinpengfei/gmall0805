package com.example.demo;
//解决8锁的问题

import java.util.concurrent.TimeUnit;

class Phone {
    synchronized void sendEmail()throws Exception {
        //枚举类点出一个单位点出一个状态
        TimeUnit.SECONDS.sleep(4);
        System.out.println( Thread.currentThread().getName()+"\t...senEmail");
    }

    synchronized void sendSMS() throws Exception{
        System.out.println( Thread.currentThread().getName()+"\t...sendSAS");
    }
}

//main线程是一切程序之母

public class Lock8 {

    public static void main(String[] args) throws InterruptedException {
/** synchronized 锁的是类的实例 当前对象 this (new Phone())存放在堆当中
 *
 *加上static 小class  大Class(类内型)
 *
 *
 */
        Phone phone = new Phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.sendEmail();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        Thread.sleep(100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    phone.sendSMS();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }

}
