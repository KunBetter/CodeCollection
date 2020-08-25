package com.kunbetter.future;

import java.util.Arrays;

/**
 * @author xinxi.li
 * date 2020/8/4
 * time 18:09
 * desc
 */
public class RemoveDuplicates {

    public static void main(String[] argv) {
        int[] arr = new int[]{5, 1, 3, 5, 5, 5, 5, 6, 6, 6, 6, 6, 5, 7, 8};
        System.out.println(removeDuplicate(arr, 5));
        System.out.println(removeDuplicates(arr));
    }

    public static int removeDuplicates(int[] arr) {
        int len = arr.length;
        int i = 0;
        while (i < len) {
            int j = i + 1;
            while (j < len) {
                if (arr[i] == arr[j]) {
                    for (int k = j; k < len - 1; k++) {
                        arr[k] = arr[k + 1];
                    }
                    len--;
                } else {
                    j++;
                }
            }
            i++;
        }

        return len;
    }

    public static int removeDuplicate(int[] arr, int val) {
        int slow = 0;
        int fast = 0;

        while (fast < arr.length) {
            if (arr[fast] != val) {
                arr[slow] = arr[fast];
                arr[fast] = val;
                slow++;
            }
            fast++;
        }

        return slow;
    }
}
