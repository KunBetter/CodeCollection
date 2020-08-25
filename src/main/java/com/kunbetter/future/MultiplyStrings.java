package com.kunbetter.future;

/**
 * @author xinxi.li
 * date 2020/8/13
 * time 15:33
 * desc : https://leetcode-cn.com/problems/multiply-strings/
 * <p>
 * num1 和 num2 的长度小于110
 * num1 和 num2 只包含数字 0-9
 * num1 和 num2 均不以零开头，除非是数字 0 本身
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理
 */
public class MultiplyStrings {

    public static void main(String[] argv) {
        MultiplyStrings ms = new MultiplyStrings();

        String num1 = "1234", num2 = "456";

        //562704
        System.out.println(ms.multiply(num1, num2));
        System.out.println(ms.add(num1, num2));
    }

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        StringBuilder res = new StringBuilder();

        String longNum = num1;
        String shortNum = num2;
        if (num1.length() < num2.length()) {
            longNum = num2;
            shortNum = num1;
        }

        for (int i = shortNum.length() - 1; i >= 0; i--) {
            int sv = shortNum.charAt(i) - '0';
            if (sv == 0) {
                continue;
            }

            StringBuilder tRes = new StringBuilder();
            for (int j = 0; j < shortNum.length() - 1 - i; j++) {
                tRes.append(0);
            }

            int jw = 0;
            for (int j = longNum.length() - 1; j >= 0; j--) {
                int lv = longNum.charAt(j) - '0';

                int cur = (lv * sv + jw) % 10;
                jw = (lv * sv + jw) / 10;
                tRes.append(cur);
            }
            if (jw > 0) {
                tRes.append(jw);
            }

            res = add(res.toString(), tRes.reverse().toString());
        }

        return res.toString();
    }

    public StringBuilder add(String num1, String num2) {
        String longNum = num1;
        String shortNum = num2;
        if (num1.length() < num2.length()) {
            longNum = num2;
            shortNum = num1;
        }

        StringBuilder res = new StringBuilder();
        int jw = 0;
        int i = 0;

        for (i = 0; i < shortNum.length(); i++) {
            int lv = longNum.charAt(longNum.length() - 1 - i) - '0';
            int sv = shortNum.charAt(shortNum.length() - 1 - i) - '0';

            int cur = (lv + sv + jw) % 10;
            jw = (lv + sv + jw) / 10;
            res.append(cur);
        }

        for (; i < longNum.length(); i++) {
            int lv = longNum.charAt(longNum.length() - 1 - i) - '0';

            int cur = (lv + jw) % 10;
            jw = (lv + jw) / 10;
            res.append(cur);
        }

        if (jw > 0) {
            res.append(jw);
        }

        return res.reverse();
    }
}
