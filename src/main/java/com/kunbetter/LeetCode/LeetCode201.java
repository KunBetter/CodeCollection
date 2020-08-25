package com.kunbetter.LeetCode;

import java.util.Stack;

public class LeetCode201 {
    public static void main(String[] argv) {
        String s = "mskEBbeKSMWXxw";
        System.out.println(makeGood(s));
        System.out.println(findKthBit(4, 11));

        int[] cuts = new int[]{1, 2, 4, 5, 6};
        System.out.println(minCost(9, cuts));
    }

    public static int minCost(int n, int[] cuts) {
        //Cuts要排序
        int len = cuts.length;
        if (len <= 1) {
            return n;
        }

        int[] CutLen = new int[len + 1];
        CutLen[0] = cuts[0];
        for (int i = 1; i < len; i++) {
            CutLen[i] = cuts[i] - cuts[i - 1];
        }
        CutLen[len] = n - cuts[len - 1];

        int minC = 0;

        int clen = CutLen.length;

        while (clen > 2) {
            int l = 0, r = 0;
            int tMinC = 100000;
            boolean flag = false;

            for (int i = 0; i < clen - 1; i++) {
                if (CutLen[i] + CutLen[i + 1] < tMinC) {
                    tMinC = CutLen[i] + CutLen[i + 1];
                    l = i;
                    r = i + 1;
                    flag = true;
                }
            }
            if (flag) {
                minC += tMinC;
                CutLen[l] = tMinC;
                for (int i = r; i < clen - 1; i++) {
                    CutLen[i] = CutLen[i + 1];
                }
                clen--;
            }
        }

        minC += CutLen[0];
        minC += CutLen[1];

        return minC;
    }

    public static char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        int lenForN = (int) Math.pow(2, n) - 1;
        int mid = (lenForN + 1) / 2;
        if (k == mid) {
            return 1;
        } else if (k > mid) {
            int lenForNd = (int) Math.pow(2, n - 1) - 1;
            char r = findKthBit(n - 1, lenForNd + 1 - (k - mid));
            if (r == '1') {
                return '0';
            } else {
                return '1';
            }
        } else {
            return findKthBit(n - 1, k);
        }
    }

    public static String makeGood(String s) {
        char A = 'A';
        char a = 'a';
        int diff = abs(A - a);

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        Stack<Byte> stack = new Stack<>();

        byte[] bs = s.getBytes();
        int i = 0;

        while (i < bs.length) {
            if (stack.empty()) {
                stack.push(bs[i]);
                i++;
            } else {
                byte f = stack.lastElement();
                if (abs(f - bs[i]) == diff) {
                    stack.pop();
                } else {
                    stack.push(bs[i]);
                }
                i++;
            }
        }

        if (stack.size() > 0) {
            byte[] res = new byte[stack.size()];
            int iX = stack.size() - 1;
            while (iX >= 0) {
                res[iX] = stack.lastElement();
                stack.pop();
                iX--;
            }

            return new String(res);
        } else {
            return "";
        }
    }

    public static int abs(int a) {
        if (a < 0) {
            return -a;
        }

        return a;
    }
}
