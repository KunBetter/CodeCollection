package com.kunbetter.future;

import java.util.concurrent.atomic.AtomicInteger;

public class MonkeyAndApple {

    private static final AtomicInteger STORE_COUNT = new AtomicInteger(9);

    public static void main(String[] argv) {
    }

    public void Apple() {

        Runnable monkey1 = () -> {
            String monkeyName = "monkey1";
            int takeoffNum = 2, storeCountByGet = 0;
            while ((storeCountByGet = STORE_COUNT.get()) >= takeoffNum) {
                if (STORE_COUNT.compareAndSet(storeCountByGet, storeCountByGet - takeoffNum)) {
                    System.out.println(monkeyName + "争抢水果成功 => " + takeoffNum);
                } else {
                    System.out.println(monkeyName + "争抢水果失败");
                }
            }
        };

        Runnable monkey2 = () -> {
            String monkeyName = "monkey2";
            int takeoffNum = 3, storeCountByGet = 0;
            while ((storeCountByGet = STORE_COUNT.get()) >= takeoffNum) {
                if (STORE_COUNT.compareAndSet(storeCountByGet, storeCountByGet - takeoffNum)) {
                    System.out.println(monkeyName + "争抢水果成功 => " + takeoffNum);
                } else {
                    System.out.println(monkeyName + "争抢水果失败");
                }
            }
        };

        Thread m1Task = new Thread(monkey1);
        Thread m2Task = new Thread(monkey2);
        m2Task.start();
        m1Task.start();
    }
}
