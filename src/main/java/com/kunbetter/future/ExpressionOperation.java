package com.kunbetter.future;

import java.util.Stack;

/**
 * @author xinxi.li
 * date 2020/8/21
 * time 16:20
 */
public class ExpressionOperation {

    public static void main(String[] argv) {
        ExpressionOperation eo = new ExpressionOperation();

        String exp = "(1+2)*3-4/3";
        System.out.println(eo.isValid(exp));
        //12+3*42/-
        System.out.println(eo.inversePolishExpression(exp));
        System.out.println(eo.calculation(exp));
    }

    /**
     * 通过逆波兰表达式计算结果：
     * a、先从右往左全部压主栈
     * b、主栈执行出栈操作
     * c、遇到数字，压入副栈。副栈数据类型为Double
     * d、遇到操作符，副栈弹出两个数字，依照操作符进行运算，运算结果再压入副栈。需注意减法、除法两个数字的顺序
     * e、副栈中最终剩余的一个数为结果，弹出并返回
     */

    public double calculation(String exp) {
        if (!isValid(exp)) {
            return Double.MIN_NORMAL;
        }

        Stack<Character> mainStack = new Stack<>();
        Stack<Double> subStack = new Stack<>();

        String pExp = inversePolishExpression(exp);
        int i = pExp.length() - 1;
        while (i >= 0) {
            char cur = pExp.charAt(i);
            mainStack.push(cur);
            i--;
        }

        while (!mainStack.empty()) {
            char top = mainStack.peek();
            if (isNum(top)) {
                subStack.push((top - '0') * 1D);
            } else if (isOp(top)) {
                double n1 = subStack.peek();
                subStack.pop();
                double n2 = subStack.peek();
                subStack.pop();

                subStack.push(cal(n1, n2, top));
            }
            mainStack.pop();
        }

        if (!subStack.empty()) {
            double finalV = subStack.peek();
            subStack.pop();

            return finalV;
        }

        return Double.MIN_NORMAL;
    }

    public double cal(double n1, double n2, char op) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                //注意顺序
                return n2 - n1;
            case '*':
                return n1 * n2;
            case '/':
                //注意顺序
                return n2 / n1;
        }

        return -1D;
    }

    /**
     * 中缀表达式转后缀表达式的方法：
     * 1.遇到操作数：直接输出（添加到后缀表达式中）
     * 2.栈为空时，遇到运算符：直接入栈
     * 3.遇到左括号：将其入栈
     * 4.遇到右括号：执行出栈操作，并将出栈的元素输出，直到弹出栈的是左括号，左括号不输出
     * 5.遇到其他运算符：加减乘除：弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈
     * 6.最终将栈中的元素依次出栈，输出
     */

    public String inversePolishExpression(String exp) {
        Stack<Character> stack = new Stack<>();
        StringBuilder res = new StringBuilder();

        int i = 0;
        while (i < exp.length()) {
            char cur = exp.charAt(i);

            if (cur == '(') {
                //遇到左括号：将其入栈
                stack.push(cur);
            } else if (isOp(cur)) {
                // 栈为空时，遇到运算符：直接入栈
                // 遇到其他运算符，加减乘除，弹出所有优先级大于或者等于该运算符的栈顶元素，然后将该运算符入栈
                while (!stack.empty()) {
                    char top = stack.peek();
                    if (opPriority(top) >= opPriority(cur)) {
                        res.append(top);
                        stack.pop();
                    } else {
                        break;
                    }
                }
                stack.push(cur);
            } else if (cur == ')') {
                //遇到右括号：执行出栈操作，并将出栈的元素输出，直到弹出栈的是左括号，左括号不输出
                while (!stack.empty()) {
                    char top = stack.peek();
                    stack.pop();
                    if (top == '(') {
                        break;
                    } else {
                        res.append(top);
                    }
                }
            } else if (isNum(cur)) {
                res.append(cur);
            }

            i++;
        }

        while (!stack.empty()) {
            char top = stack.peek();
            stack.pop();
            res.append(top);
        }

        return res.toString();
    }

    public boolean isOp(char b) {
        return b == '+' || b == '-' || b == '*' || b == '/';
    }

    public boolean isNum(char b) {
        int t = b - '0';
        return t >= 0 && t <= 9;
    }

    public int opPriority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }

        return -1;
    }

    /**
     * 如何判断表达式可以运算：
     * a、双目运算符数字完备
     * b、除法分母不为0
     * c、括号数量匹配
     */
    public boolean isValid(String exp) {
        Stack<Byte> stack = new Stack<>();

        int i = 0;
        while (i < exp.length()) {
            char cur = exp.charAt(i);
            if (cur == '(') {
                stack.push((byte) cur);
            } else if (cur == ')') {
                if (!stack.empty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
            i++;
        }

        return stack.empty();
    }
}