package com.kunbetter.future;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 17:14
 * desc
 */
@SuppressWarnings("ALL")
public class Singleton {

    private volatile static Singleton singleton = null;

    private Singleton() {
    }

    public static Singleton getSingleton() {
        if (null == singleton) {
            synchronized (Singleton.class) {
                if (null == singleton) {
                    singleton = new Singleton();
                }
            }
        }

        return singleton;
    }
}
