package com.kunbetter.future;

public class Atoi {

    public static void main(String[] argv) {
        String s1 = "    234  ";//前后空格剔除
        String s2 = "    +234";//正负号
        String s3 = "-234";
        String s4 = "+    234";//正确解析？
        String s5 = "  -    234";
        String s6 = "234a";//无法解析
        String s7 = "234";//
        String s8 = "0234";//最高位为0
        String s9 = "-0234";//最高位为0
        String s10 = "234*";//无法解析
        String s11 = "2342348923749823749";//越界
        String s12 = "2342348234234892372342348923749823749498237492342348923749823749923749823749";//越界
        String s13 = "-2147483647";
        String s14 = "-2147483649";//越界

        System.out.println(atoi(s1));
        System.out.println(atoi(s2));
        System.out.println(atoi(s3));
        System.out.println(atoi(s4));
        System.out.println(atoi(s5));
        System.out.println(atoi(s6));
        System.out.println(atoi(s7));
        System.out.println(atoi(s8));
        System.out.println(atoi(s9));
        System.out.println(atoi(s10));
        System.out.println(atoi(s11));
        System.out.println(atoi(s12));
        System.out.println(atoi(s13));
        System.out.println(atoi(s14));
    }

    public static int atoi(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }

        //移除空格
        int head = 0, tail = s.length() - 1;
        while (s.charAt(head) == ' ') {
            head++;
        }
        while (s.charAt(tail) == ' ') {
            tail--;
        }

        //正负号判断
        boolean flag = true;
        if (s.charAt(head) == '+') {
            head++;
        } else if (s.charAt(head) == '-') {
            flag = false;
            head++;
        }

        //正负号后空格移除
        while (s.charAt(head) == ' ') {
            head++;
        }

        long sum = 0;
        boolean haveNoNumber = false;
        boolean yuejie = false;
        for (int i = head; i <= tail; i++) {
            char current = s.charAt(i);
            if (current >= '0' && current <= '9') {
                sum = sum * 10 + current - '0';
                //整数的最大值和最小值的绝对值是不一样的
                if ((!flag && -1 * sum < Integer.MIN_VALUE) || sum > Integer.MAX_VALUE) {
                    //越界
                    yuejie = true;
                    break;
                }
            } else {
                //有非法字符
                haveNoNumber = true;
                break;
            }
        }

        if (yuejie || haveNoNumber) {
            return -1;
        }

        if (!flag) {
            sum = -1 * sum;
        }

        return (int) sum;
    }
}
