package com.shuai.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSortTest {

    public static void main(String[] args) {

        int[] arr = {45, 12, 37, 26, 31};
        sort2(arr);
        System.out.println("排序完成：" + Arrays.toString(arr));

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { //i值：0,1,2,3，表示第1,2,3,4趟排序
            for (int j = 0; j < arr.length - 1 - i; j++) { //每趟排序，对应的循环次数：4,3,2,1
                if (arr[j] > arr[j + 1]) {
                    // 如果前面的数比后面的数大，则交换位置
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟循环后的数组：" + Arrays.toString(arr));
        }
    }

    /**
     * 优化版冒泡排序
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) { //i值：0,1,2,3，表示第1,2,3,4趟排序
            boolean flag = false;// 标识变量，表示是否进行过交换
            for (int j = 0; j < arr.length - 1 - i; j++) { //每趟排序，对应的循环次数：4,3,2,1
                if (arr[j] > arr[j + 1]) {
                    // 如果前面的数比后面的数大，则交换位置
                    flag = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            System.out.println("第" + (i + 1) + "趟循环后的数组：" + Arrays.toString(arr));
            if (!flag) {// 在一趟排序中，一次交换都没有发生过,则结束循环
                break;
            }
        }
    }
}
