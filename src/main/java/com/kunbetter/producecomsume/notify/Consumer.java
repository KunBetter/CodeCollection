package com.kunbetter.producecomsume.notify;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 17:23
 * desc
 */
public class Consumer implements Runnable {

    private Storage storage;

    public Consumer() {
    }

    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3000);
                storage.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
