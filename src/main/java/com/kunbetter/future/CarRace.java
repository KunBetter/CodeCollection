package com.kunbetter.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CarRace {
    private int epoch;
    private final ExecutorService pool = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;
    private final List<Car> list = new ArrayList<>();
    private final int FINISH_LINE = 100;
    private final Random random = new Random(System.currentTimeMillis());

    public CarRace(int count) {
        this.barrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    sb.append("=");
                }
                System.out.println(sb);
                System.out.println("this is epoch " + epoch++);
                for (Car car : list) {
                    System.out.println(car);
                }
                for (Car car : list) {
                    if (car.getStride() > FINISH_LINE) {
                        System.out.println("car " + car.getId() + " Won");
                        pool.shutdownNow();
                        return;
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(30L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        for (int i = 0; i < count; i++) {
            Car car = new Car(barrier, random);
            list.add(car);
            pool.execute(car);
        }
    }

    public static void main(String[] args) {
        CarRace carRace = new CarRace(10);
    }
}

class Car implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private CyclicBarrier barrier;
    private int stride;
    private Random random;

    private StringBuilder sb = new StringBuilder("Car " + id + " ");

    public Car(CyclicBarrier barrier, Random random) {
        this.barrier = barrier;
        this.random = random;
    }

    public synchronized int getStride() {
        return stride;
    }

    public int getId() {
        return id;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    int temp = random.nextInt(3);
                    this.stride += temp;
                    for (int i = 0; i < temp; i++) {
                        sb.append("*");
                    }
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return sb.toString();
    }
}
