package com.example.demo;


import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 *
 * 1.故障现象
 *    ConcurrentModificationException 并发修改异常
 *  2.导致原因
 *     没有加锁  tostring 读写不一致  if（modcount==expected modcount）
 *  3.解决方法
 *      三者线程对都不安全 ArrayList的层始容量为零 在第一次调用add方法是将其容量设置为10 后面进行1.5倍的扩容
 *      第三次容量扩为22
 *
 *  1.new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList());
 *  //new Vector<>();// new ArrayList<>();
 *  2.//new CopyOnWriteArraySet<>();//Collections.synchronizedSet(new HashSet<>()); //new HashSet<>();
 *    hashset 底层是一个hashmap map = new HashMap<>();   set 中存入的值是map中的key value 值是Object的常量
 *  3.       new ConcurrentHashMap<>();

 *
 *  4.优化建议
 *
 *
 *
 * new CopyOnWriteArrayList<>() 使用场景是什么  底层是什么 写实复制技术????
 * conlections  类....???
 *
 * 虚假唤醒
 * 写实复制
 */


   /*ArrayList 底层扩容
    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }*/
   /* 导致故障的原因
   final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }

    */

/* 写实复制的源码分析
 public boolean add(E e) {
  final ReentrantLock lock = this.lock;
        lock.lock();
        try {
        Object[] elements = getArray();
        int len = elements.length;
        Object[] newElements = Arrays.copyOf(elements, len + 1);
        newElements[len] = e;
        setArray(newElements);
        return true;
        } finally {
        lock.unlock();
        }
         }
*/
public class TestList {
    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList());//new Vector<>();// new ArrayList<>();
        //List<String> list1 = new CopyOnWriteArrayList<>();

        /*list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");*/

        // list.forEach(System.out::println);
       /* for (String elment:list) {
            System.out.println(elment);
        }*/
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 6));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
