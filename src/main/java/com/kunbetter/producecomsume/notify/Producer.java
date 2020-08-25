package com.kunbetter.producecomsume.notify;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 17:23
 * desc
 */
public class Producer implements Runnable {
    private Storage storage;

    public Producer() {
    }

    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                storage.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
