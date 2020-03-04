package com.shuai.stack;

/**
 * 计算工具类
 */
public class CalculatorUtil {

    /**
     * 获取运算符的优先级
     *
     * @param operator
     * @return
     */
    public static int getPriority(int operator) {

        if (operator == '*' || operator == '/') {
            return 2;
        }
        if (operator == '+' || operator == '-') {
            return 1;
        }
        return -1;
    }

    /**
     * 是否是运算符
     *
     * @param val
     * @return
     */
    public static boolean isOperator(int val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算两个数的结果
     *
     * @param num1     数1
     * @param num2     数2
     * @param operator 操作符
     * @return
     */
    public static int calculate(int num1, int num2, int operator) {
        if (!isOperator(operator)) {
            throw new RuntimeException(operator + "非操作符");
        }
        int result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        return result;
    }

}
