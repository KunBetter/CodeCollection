package com.kunbetter.future;

/**
 * @author xinxi.li
 * date 2020/8/21
 * time 11:52
 * desc
 */
public class FindKthLargest {

    public static void main(String[] argv) {
        FindKthLargest fk = new FindKthLargest();

        int[] nums = new int[]{3, 4, 6, 1, 2, 7};
        System.out.println(fk.findKthLargest(nums, 3));
    }

    public int findKthLargest(int[] nums, int k) {
        return quickFind(nums, k, 0, nums.length - 1);
    }

    private int quickFind(int[] nums, int k, int s, int e) {
        if (s >= e) {
            if (s == nums.length - k) {
                return nums[k];
            }
            return -1;
        }

        int p = part(nums, s, e);
        if (p == nums.length - k) {
            return nums[p];
        } else if (p < nums.length - k) {
            return quickFind(nums, k, p + 1, e);
        } else {
            return quickFind(nums, k, s, p - 1);
        }
    }

    private int part(int[] nums, int s, int e) {
        int p = nums[e];
        int i = s - 1;
        int j = s;
        while (j <= e) {
            if (nums[j] <= p) {
                int t = nums[i + 1];
                nums[i + 1] = nums[j];
                nums[j] = t;
                i++;
            }
            j++;
        }

        return i;
    }
}
