package com.shuai._algorithm.dynamic;

import java.util.ArrayList;
import java.util.List;

/**
 * 背包问题（01背包）
 */
public class KnapsackTest {

    public static void main(String[] args) {

        // 创建物品List
        List<Goods> list = new ArrayList<>();
        list.add(new Goods(1, 1500));
        list.add(new Goods(4, 3000));//3500
        list.add(new Goods(3, 2000));

        // 物品个数
        int N = list.size();
        // 背包容量
        int C = 4;
        // V[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] V = new int[N + 1][C + 1];
        // 装入路径记录
        int[][] path = new int[N + 1][C + 1];

        // 动态规划处理
        // 不处理第一行 i是从1开始的
        for (int i = 1; i < V.length; i++) {
            // 不处理第一列, j是从1开始的
            for (int j = 1; j < V[0].length; j++) {
                // 一、当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
                if (list.get(i - 1).weight > j) {
                    // 因为i从1开始，list从0开始，所以这里list.get(i-1)
                    V[i][j] = V[i - 1][j];
                }
                // 二、当准备加入的新增的商品的容量小于等于当前背包的容量时，则要比较一下以下两种策略：
                else if (list.get(i - 1).weight <= j) {
                    // 1、使用上一行单元格的装入策略
                    int value1 = V[i - 1][j];
                    // 2、装入当前商品，并把剩余空间装入最大value
                    int value2 = list.get(i - 1).value + V[i - 1][j - list.get(i - 1).weight];
                    if (value2 > value1) {
                        V[i][j] = value2;
                        // 记录装入路径
                        path[i][j] = 1;
                    } else {
                        V[i][j] = value1;
                    }
                }
            }
        }

        System.out.println("\n=========表格结果=========");

        // 遍历V[i][j] 输出表格结果
        for (int i = 0; i < V.length; i++) {
            for (int j = 0; j < V[0].length; j++) {
                System.out.print(V[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("\n=========装入结果=========");

        // 倒序遍历path[i][j] 输出第几个商品放入背包的结果
        int i = path.length - 1;// 列的最大下标
        int j = path[0].length - 1;// 行的最大下标
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= list.get(i - 1).weight;
            }
            i--;
        }
    }
}


class Goods {
    //物品的重量
    public int weight;
    //物品的价值
    public int value;

    public Goods(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}
