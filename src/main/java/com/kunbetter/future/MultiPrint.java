package com.kunbetter.future;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xinxi.li
 * date 2020/8/6
 * time 14:54
 * desc
 */
public class MultiPrint {

    public static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws Exception {
        ArrayList<Thread> list = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Condition> conditionList = new ArrayList<>();
        boolean[] flag = new boolean[n];
        for (int i = 0; i < n; i++) {
            conditionList.add(lock.newCondition());
        }
        for (int i = 0; i < n; i++) {
            final int j = i;
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        int c = j;
                        if (c > 0) {
                            //while条件限制
                            while (!flag[c]) {
                                conditionList.get(j).await();
                            }
                        }
                        System.out.println(c + 1);

                        if (c + 1 < n) {
                            flag[c + 1] = true;//一种标识，类似生产消费模型的数量标识
                            conditionList.get(c + 1).signal();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }));
        }
        for (int i = 0; i < n; i++) {
            list.get(i).start();
        }
    }

}
