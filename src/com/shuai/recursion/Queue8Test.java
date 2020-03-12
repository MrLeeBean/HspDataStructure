package com.shuai.recursion;

/**
 * 8皇后问题
 */
public class Queue8Test {

    public static void main(String[] args) {

        Queue8Test queue8 = new Queue8Test();
        queue8.check(0);
        System.out.println("总共可行解法数：" + queue8.resultCount);
        System.out.println("总共检测冲突数：" + queue8.judgeCount);

    }

    int max = 8;//定义一个max表示共有多少个皇后
    int[] array = new int[max];//定义数组array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int resultCount;//可行解法次数统计
    int judgeCount; //检测冲突次数统计

    /**
     * 打印皇后摆放位置
     */
    public void print() {
        resultCount++;
        for (int i = 0; i < array.length; i++) {
            System.out.print((i == 0 ? "[ " : "") + array[i] + " " + (i == array.length - 1 ? "]" : ""));
        }
        System.out.println();
    }

    /**
     * 检测第n个皇后是否放置正确
     * 即检测该皇后是否和前面已经摆放的皇后冲突
     *
     * @param n 表示第n个皇后，即第n行
     * @return true:摆放正确无冲突，false:摆放错误发生冲突
     */
    public boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //1. array[i] == array[n]  表示判断 第n个皇后是否和前面的第i个皇后在同一列
            //2. Math.abs(n-i) == Math.abs(array[n] - array[i])  表示判断第n个皇后是否和第i皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(i - n) == Math.abs(array[i] - array[n])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 执行放置第n个皇后
     * 特别注意： check 是 每一次递归时，进入到check中都有  for(int i = 0; i < max; i++)，因此会有回溯
     *
     * @param n 表示第n个皇后，即第n行
     */
    public void check(int n) {
        if (n == max) { //n=8.说明现在是第9个皇后了，8个皇后已经全部放好，
            print();
            return;
        }
        //遍历当前行（n行）的所有位置（列位置），依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //把当前这个皇后 n , 放到该行的第i列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {
                //不冲突，则接着放n+1个皇后,即开始递归
                check(n + 1);
            }
            //如果冲突，就继续循环去执行array[n] = i; 即将第n个皇后，放置在本行 后移的一个位置
        }
    }
}
