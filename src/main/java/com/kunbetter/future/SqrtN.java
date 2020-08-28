package com.kunbetter.future;

/**
 * User: xinxi.li
 * Date: 2020/8/27
 * Time: 22:31
 */
public class SqrtN {

    public static void main(String[] argv) {
        System.out.println(sqrtV1(2));
        System.out.println(sqrtV2(2));
        System.out.println(sqrtV3(2));
    }

    /**
     * 解法一：x^2 = 2 公式转化为 x = (k/x + x)/2；给x赋一个初始值，然后迭代计算
     */
    public static double sqrtV1(int k) {
        double x, t = k;
        do {
            x = t;
            t = (k / x + x) / 2;
        } while (x - t > 0.00002);
        return x;
    }

    /**
     * 解法二：牛顿迭代法 y = x^2 - k, x{n+1} = x{n} - f(x{n})/f'(x{n}  => x{n+1} = (x{n}^2+k)/2*x{n}
     * https://blog.csdn.net/xiaxl/article/details/94438829
     */
    public static double sqrtV2(int k) {
        double x, t = k;
        do {
            x = t;
            t = (x * x + k) / (2 * x);
        } while (x - t > 0.00002);

        return x;
    }

    /**
     * 解法三：二分查找
     * 从 0 ~ k 之间取中间值m，m*m 与 k 做比较
     * 若 mm > k ; 则下次取 0 ~ mm 中间值比较
     * 若mm < k ; 则下次取 mm ~ k 中间值比较
     * 逐渐逼近
     */
    public static double sqrtV3(int k) {
        double low = 0d;
        double high = k;
        double mid = (low + high) / 2;
        do {
            if (mid * mid > k) {
                high = mid;
            } else if (mid * mid < k) {
                low = mid;
            } else if (Math.abs(mid * mid - k) < 0.00002) {
                return mid;
            }

            mid = (low + high) / 2;
        } while (Math.abs(mid * mid - k) > 0.00002);

        return mid;
    }
}
