package com.kunbetter.producecomsume.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 17:30
 * desc
 * BlockingQueue是JDK5.0的新增内容，它是一个已经在内部实现了同步的队列，
 * 实现方式采用的是我们第2种await() / signal()方法。
 * 它可以在生成对象时指定容量大小，用于阻塞操作的是put()和take()方法。
 * <p>
 * put()方法：类似于我们上面的生产者线程，容量达到最大时，自动阻塞。
 * take()方法：类似于我们上面的消费者线程，容量为0时，自动阻塞。
 */
public class Storage {

    // 仓库存储的载体
    private LinkedBlockingQueue<Object> list = new LinkedBlockingQueue<>(10);

    public void produce() {
        try {
            list.put(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void consume() {
        try {
            list.take();
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费了一个产品，现库存" + list.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
