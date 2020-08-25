package com.kunbetter.future;

import java.util.LinkedList;
import java.util.Timer;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class QueueWithTimeOut {
    private LinkedList<Node> que;
    private Timer timer;
    private int size;
    private int curSize = 0;

    QueueWithTimeOut(int size, int delay, int interval) {
        this.size = size;
        this.que = new LinkedList();
        this.timer = new Timer();

        //使用thread
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(delay);
            } catch (Exception e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    scanAndDelete();
                    Thread.sleep(interval);//注意此处要用类去调用静态方法，直接用实例thread调用会报其可能尚未实例化
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "scanThread");
        thread.start();
    }

    public class Node {
        Object key;
        long timeout; //这里timeout存放的是currentTime + timeout

        Node(Object key, long timeout) {
            this.key = key;
            this.timeout = timeout;
        }

        public long getTimeout() {
            return timeout;
        }
    }

    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public void offer(Object key, long currentTime, long timeout) throws Exception {
        timeout += currentTime;
        if (curSize > size) {
            throw new Exception("size 已满");
        }
        rwlock.writeLock().lock();
        Node node = new Node(key, timeout);
        que.offer(node);
        curSize++;
        rwlock.writeLock().unlock();
    }

    public synchronized Object poll() {
        rwlock.readLock().lock();
        if (!que.isEmpty()) {
            return que.poll();
        }
        rwlock.readLock().unlock();
        return null;
    }

    private void scanAndDelete() {
        rwlock.readLock().lock();
        if (!que.isEmpty()) {
            int len = que.size();
            for (int i = 0; i < len; i++) {
                if (que.get(i).getTimeout() < getCurrentTime()) {
                    rwlock.writeLock().lock();
                    que.remove(i);
                    rwlock.writeLock().unlock();
                }
            }
        }
        rwlock.readLock().unlock();
    }
}
