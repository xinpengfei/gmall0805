package com.example.demo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


class ShoreResource{
    Map<String,String> map=new HashMap<>();
    public void writer(String key,String vlaue){
        System.out.println(Thread.currentThread().getName()+"\t写数据开始");
        map.put(key,vlaue);
        System.out.println(Thread.currentThread().getName()+"\t写数据结束");


    }
    public void read(String key){
        System.out.println(Thread.currentThread().getName()+"\t读取数据开始");
        String result = map.get(key);
        System.out.println(Thread.currentThread().getName()+"\t读取数据结束"+result);
    }
}

/**1.  不加锁出现读取的数据不一致
 *
 * 2.加入lock锁之后一致保证了但是并发性下降
 *
 * 3. 加入readwriterlock写入唯一读高性能
 *
 *
 * @author:xpf
 * @date: 2019/12/16 20:34
 */
public class ReadWriterLockDamo {
    public static void main(String[] args) {
        ShoreResource shoreResource = new ShoreResource();
        for (int i = 0; i <50; i++) {
            final int temp=i;
            new Thread(()->{
                shoreResource.writer(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i <50; i++) {
            final int temp=i;
            new Thread(()->{
                shoreResource.read(temp+"");
            },String.valueOf(i)).start();
        }
    }

}

/**countdownlatch开始点火发射（count计数 down向下 lach占有）
 *
 *到倒计时后发射
 */

class CountDownLatchDamo{
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"离开教室"+countDownLatch.getCount());
                countDownLatch.countDown();

            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("最后执行的线程");
    }

}
/**人到起后开会
 *cyclic barrier循环 屏障界限
 *
 *
 *
 */
class CyclicBarrierDamo{
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println(Thread.currentThread().getName() + "集齐7颗龙珠，召唤李金龙");
        });
        for (int i = 0; i <7 ; i++) {
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第几个龙珠："+temp);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
    }

}



class SemephpreDamo{
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

    }
}
/**
 *Runnable() 和Callable<>()创建线程时有什么不同
 * ① 方法名不同 Run 和call 方法 ②有误返回值 void  string ③callable回抛异常
 *
 *
 *
 */

class  Mycallalble implements Callable{



    @Override
    public Object call() throws Exception {
        System.out.println("nnn");
        return 2;
    }
}

class CallableDam{
    public static void main(String[] args) {
        FutureTask futureTask=new FutureTask(new Mycallalble());
        new Thread(futureTask,"a").start();
    }
}


//数组  链表  红黑树
class  BloackingQueueDamo{
    public static void main(String[] args) {
        BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("m"));
        System.out.println(arrayBlockingQueue.add("v"));
        System.out.println(arrayBlockingQueue.add("v"));
    }

}