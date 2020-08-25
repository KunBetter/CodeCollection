package com.kunbetter.future;

import java.util.Arrays;
import java.util.Random;

/**
 * @author xinxi.li
 * date 2020/8/7
 * time 18:15
 * desc
 */
public class ArrayShuffle {

    public static void main(String[] argv) {
        int[] arr = new int[]{3, 5, 7, 678, 8, 98, 65};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }

    //随机打乱一个数组
    public static void shuffle(int[] arr) {
        if (arr.length <= 1) {
            return;
        }

        Random random = new Random();

        for (int i = 0; i < arr.length - 1; i++) {
            int ix = randomIx(random, arr.length - i - 1) + i + 1;

            int tmp = arr[i];
            arr[i] = arr[ix];
            arr[ix] = tmp;
        }
    }

    private static int randomIx(Random random, int n) {
        return random.nextInt(n) % n;
    }
}
