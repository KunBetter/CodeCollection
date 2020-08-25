package com.kunbetter.future;

import java.util.LinkedList;
import java.util.List;

/**
 * User: xinxi.li
 * Date: 2020/8/14
 * Time: 23:29
 */
public class FindDuplicated {

    /**
     * 给定一个整数数组a，其中1<= a[i]<=n(n为数组的长度)，
     * 其中有些元素出现两次而其他元素出现一次。
     * 找到所有出现两次的元素。
     */

    public static void main(String[] argv) {
        int[] a = new int[]{4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(findDuplicated(a));
    }

    public static List<Integer> findDuplicated(int[] nums) {
        List<Integer> res = new LinkedList<>();

        int i = 0;
        while (i < nums.length) {
            if (nums[i] > 0) {
                int n = nums[i];
                if (nums[n - 1] > 0) {
                    nums[i] = nums[n - 1];
                    nums[n - 1] = -1;
                } else if (nums[n - 1] == 0) {
                    nums[n - 1] = -1;
                    nums[i] = 0;
                } else {
                    res.add(nums[i]);
                    nums[i] = 0;
                }
            } else {
                i++;
            }
        }

        return res;
    }
}
