package com.example.demo;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Resoure {
    private int numbder = 30;

    private Lock lock = new ReentrantLock();


}

/**
 * @author:xpf
 * @date: 2019/12/17 10:57
 */
public class ThreadPoolExcetureDamo {
    public static void main(String[] args) {
        ExecutorService threadpool = Executors.newFixedThreadPool(3);//线程池里固定线程
        ExecutorService threadpool2 = Executors.newSingleThreadExecutor();//有一个线程
        ExecutorService threadpool3 = Executors.newCachedThreadPool();//看情况

        for (int i = 0; i < 30; i++) {


        }
    }
}

class ThreadPoolExecotorDamo {
    public static void main(String[] args) {
        //ExecutorService threadPool3=Executors.newFixedThreadPool(5);
        ExecutorService threadpool1=Executors.newCachedThreadPool();
        ///ExecutorService threaspoll2 = Executors.newSingleThreadExecutor();
        ExecutorService threadPool= new ThreadPoolExecutor(
                2,
                3,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 20; i++) {
            final int emple = i;
            threadPool.execute(() -> {
                System.out.println("那个线程：" + Thread.currentThread().getName() + "处理那个业务" + emple);
            });
        }
    }

}