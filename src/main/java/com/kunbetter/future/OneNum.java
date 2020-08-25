package com.kunbetter.future;

/**
 * @author xinxi.li
 * date 2020/8/7
 * time 18:14
 * desc
 */
public class OneNum {

    public static void main(String[] argv) {
        System.out.println(oneCount(7));
    }

    //统计整数二进制里1的个数
    public static int oneCount(int n) {
        int t = n;
        int count = 0;
        while (t > 0) {
            t = t & (t - 1);
            count++;
        }

        return count;
    }
}
