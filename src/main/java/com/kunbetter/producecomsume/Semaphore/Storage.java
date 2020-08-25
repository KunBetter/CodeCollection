package com.kunbetter.producecomsume.Semaphore;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 17:31
 * desc
 * Semaphore是一种基于计数的信号量。它可以设定一个阈值，
 * 基于此，多个线程竞争获取许可信号，做完自己的申请后归还，
 * 超过阈值后，线程申请许可信号将会被阻塞。Semaphore可以用来构建一些对象池，
 * 资源池之类的，比如数据库连接池，我们也可以创建计数为1的Semaphore，
 * 将其作为一种类似互斥锁的机制，这也叫二元信号量，表示两种互斥状态。
 * 计数为0的Semaphore是可以release的，然后就可以acquire
 * （即一开始使线程阻塞从而完成其他执行。）。
 */
public class Storage {

    // 仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();

    // 仓库的最大容量
    final Semaphore notFull = new Semaphore(10);

    // 将线程挂起，等待其他来触发
    final Semaphore notEmpty = new Semaphore(0);

    // 互斥锁
    final Semaphore mutex = new Semaphore(1);

    public void produce() {
        try {
            notFull.acquire();
            mutex.acquire();
            list.add(new Object());
            System.out.println("【生产者" + Thread.currentThread().getName() + "】生产一个产品，现库存" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notEmpty.release();
        }
    }

    public void consume() {
        try {
            notEmpty.acquire();
            mutex.acquire();
            list.remove();
            System.out.println("【消费者" + Thread.currentThread().getName()
                    + "】消费一个产品，现库存" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mutex.release();
            notFull.release();
        }
    }
}
