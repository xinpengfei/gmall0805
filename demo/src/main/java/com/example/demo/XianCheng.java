package com.example.demo;
//juc 并发工具包
/*线程   进程
并行  并发
线程的创建  实现Runnable 接口 继承 thread父类
1.Thread()
2.Thread(Runnable target)
3.Thread(Runnable target, String name)
4.Thread(String name)

 */
//线程 操作  资源类


public class XianCheng {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        //Thread(Runnable target, String name)
/*for (int i = 0; i < 35; i++) {
                ticket.sale();
            }

 */
        new Thread(() -> {
            for (int i = 0; i < 35; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {
                    ticket.sale();
                }

            }
        }, "B").start();

       Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {
                    ticket.sale();
                }

            }
        });


        t3.start();


    }


}
/*juc 是并发工具包
lock


 */