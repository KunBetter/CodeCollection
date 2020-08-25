package com.kunbetter.producecomsume.notify;

import java.util.LinkedList;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 17:23
 * desc
 * <p>
 * 当缓冲区已满时，生产者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行；
 * 当缓冲区已空时，消费者线程停止执行，放弃锁，使自己处于等状态，让其他线程执行。
 * <p>
 * 当生产者向缓冲区放入一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态；
 * 当消费者从缓冲区取出一个产品时，向其他等待的线程发出可执行的通知，同时放弃锁，使自己处于等待状态。
 */
public class Storage {

    // 仓库容量
    private final int MAX_SIZE = 10;

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    public void produce() {
        synchronized (list) {
            while (list.size() + 1 > MAX_SIZE) {
                System.out.println("【生产者" + Thread.currentThread().getName() + "】仓库已满");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());
            list.notifyAll();
        }
    }

    public void consume() {
        synchronized (list) {
            while (list.size() == 0) {
                System.out.println("【消费者" + Thread.currentThread().getName() + "】仓库为空");
                try {
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.remove();
            System.out.println("【消费者" + Thread.currentThread().getName() + "】消费一个产品，现库存" + list.size());
            list.notifyAll();
        }
    }
}
