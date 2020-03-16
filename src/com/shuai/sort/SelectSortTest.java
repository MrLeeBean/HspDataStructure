package com.shuai.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSortTest {
    public static void main(String[] args) {

//        int[] arr = {45, 12, 37, 26, 31};
//        sort2(arr);
//        System.out.println("排序完成：" + Arrays.toString(arr));

        int[] arr = SortUtil.getBigArr();
        SortUtil.showTime();
        sort2(arr);
        SortUtil.showTime();
    }


    /**
     * 选择排序
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//i值，表示开始比较的位置 : 0,1,2,3  不包括最后一位 arr.length-1
            for (int j = i + 1; j <= arr.length - 1; j++) {//j值，从i的下一位开始，和arr[i]比较，直到最后一位即 arr.length-1 的位置
                if (arr[i] > arr[j]) {
                    // 如果 arr[i] 比后面的数大，则直接交换位置
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            //System.out.println("第" + (i + 1) + "趟循环后的数组：" + Arrays.toString(arr));
        }
    }

    /**
     * 选择排序优化版
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {//i值，表示开始比较的位置 : 0,1,2,3  不包括最后一位 arr.length-1
            int minIndex = i;//假定最小值的index为i
            int min = arr[i];//假定最小值是arr[i]
            for (int j = i + 1; j <= arr.length - 1; j++) {//j值，从i的下一位开始，和arr[i]比较，直到最后一位即 arr.length-1 的位置
                if (min > arr[j]) {
                    min = arr[j];// 重置min
                    minIndex = j;// 重置minIndex
                }
            }
            //交换arr[i]和arr[minIndex]
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
            //System.out.println("第" + (i + 1) + "趟循环后的数组：" + Arrays.toString(arr));
        }
    }
}
