package com.kunbetter.future;

/**
 * @author xinxi.li
 * date 2020/8/12
 * time 14:27
 * desc
 */
public class Ant {

    public static void main(String[] argv) {
        int m = 32;
        System.out.println(judge(m));
    }

    public static boolean judge(int m) {
        //边界
        if (m < 1) {
            return false;
        }

        int t = m & (m - 1);

        return t == 0;
    }
}
