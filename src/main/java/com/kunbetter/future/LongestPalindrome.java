package com.kunbetter.future;

/**
 * @author xinxi.li
 * date 2020/8/5
 * time 16:59
 * desc 最大回文子串
 */
public class LongestPalindrome {

    public static void main(String[] argv) {
        String str = "cbbd";
        System.out.println(longestPalindrome(str));
    }

    public static String longestPalindrome(String str) {
        if (str == null || str.length() < 1) {
            return "";
        }
        String s1 = innerLongestPalindrome(str, false);
        String s2 = innerLongestPalindrome(str, true);

        if (s1.length() > s2.length()) {
            return s1;
        }

        return s2;
    }

    public static String innerLongestPalindrome(String str, boolean twoCenter) {
        int len = str.length();
        int i = 0;
        int maxLen = 0, l = 0, r = 0;
        byte[] bs = str.getBytes();
        while (i < len) {
            int left = i;
            int right = i;
            if (twoCenter && right + 1 < len && bs[right] == bs[right + 1]) {
                right++;
            }
            while (left - 1 >= 0 && right + 1 < len && bs[left - 1] == bs[right + 1]) {
                left--;
                right++;
            }
            if (maxLen < right - left + 1) {
                maxLen = right - left + 1;
                l = left;
                r = right;
            }

            i++;
        }
        return str.substring(l, r + 1);
    }
}
