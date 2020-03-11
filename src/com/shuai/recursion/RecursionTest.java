package com.shuai.recursion;

/**
 * 递归测试
 */
public class RecursionTest {

    public static void main(String[] args) {
        int result = factorial(4);
        System.out.println(result);
    }

    /**
     * 打印问题
     *
     * @param n
     */
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println("n=" + n);
        }
    }

    /**
     * 阶乘问题
     *
     * @param n
     * @return
     */
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }


}



