package com.kunbetter.producecomsume.notify;

import java.util.LinkedList;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 17:15
 * desc
 * <p>
 * 实现方法：
 * wait() / notify()方法
 * await() / signal()方法
 * BlockingQueue阻塞队列方法
 * 信号量
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        Storage storage = new Storage();
        Thread p1 = new Thread(new Producer(storage));
        Thread p2 = new Thread(new Producer(storage));
        Thread p3 = new Thread(new Producer(storage));

        Thread c1 = new Thread(new Consumer(storage));
        Thread c2 = new Thread(new Consumer(storage));
        Thread c3 = new Thread(new Consumer(storage));

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }
}

