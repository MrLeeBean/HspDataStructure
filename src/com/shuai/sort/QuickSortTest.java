package com.shuai.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSortTest {

    public static void main(String[] args) {

        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        sort(arr, 0, arr.length - 1);
        System.out.println("排序完成：" + Arrays.toString(arr));

//        int[] arr = SortUtil.getBigArr();
//        SortUtil.showTime2();
//        sort(arr, 0, arr.length - 1);
//        SortUtil.showTime2();

    }


    /**
     * 快速排序
     *
     * @param arr  数组
     * @param low  左侧位置index
     * @param high 右侧位置index
     */
    public static void sort(int[] arr, int low, int high) {
        int i, j, flag, t;
        if (low > high) {
            return;
        }
        i = low;
        j = high;
        //flag就是基准位
        flag = arr[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (flag <= arr[j] && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (flag >= arr[i] && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }
        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = flag;
        //递归调用左半数组
        sort(arr, low, j - 1);
        //递归调用右半数组
        sort(arr, j + 1, high);
    }

}
