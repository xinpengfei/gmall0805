package com.example.demo;
/*class X {
   private final ReentrantLock lock = new ReentrantLock();
   // ...

   public void m() {
     lock.lock();  // block until condition holds
     try {
       // ... method body
     } finally {
       lock.unlock()
     }
   }
 }
 private  int number=30;
    public synchronized void sale(){
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"\t 买出了第："+(number--)+"还剩下："+number);
        }
    }
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private Lock lock = new ReentrantLock();
    private int number = 30;

    public void sale() {
        lock.lock();
        try{
        if (number>0){
            System.out.println(Thread.currentThread().getName()+"\t 买出了第："+(number--)+"还剩下："+number);
        }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

}
