package com.shuai.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSortTest {

    public static void main(String[] args) {

//        int[] arr = {45, 12, 37, 26, 31};
//        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
//        System.out.println("排序完成：" + Arrays.toString(arr));

        SortUtil.showTime2();
        int[] arr = SortUtil.getBigArr();
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        SortUtil.showTime2();

    }


    /**
     * 分治策略 --> 分
     *
     * @param arr   排序的原始数组
     * @param left  最左边索引
     * @param right 最右边索引
     * @param temp  做中转的temp数组
     */
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引

            mergeSort(arr, left, mid, temp);//向左递归进行分解
            mergeSort(arr, mid + 1, right, temp);//向右递归进行分解

            merge(arr, left, mid, right, temp);//合并
        }
    }

    /**
     * 分治策略 --> 治
     *
     * @param arr   排序的原始数组
     * @param left  最左边索引
     * @param mid   中间索引
     * @param right 最右边索引
     * @param temp  做中转的temp数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left;   // 初始化i, 左边有序序列的初始索引
        int j = mid + 1;// 初始化j, 右边有序序列的初始索引
        int t = left;   // 指向temp数组的当前索引

        // (一)
        // 先把左右两边(有序)的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {
            // 如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            // 即将左边的当前元素，填充到temp数组，然后 t++, i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                i++;
                t++;
            } else {// 反之,将右边有序序列的当前元素，填充到temp数组
                temp[t] = arr[j];
                j++;
                t++;
            }
        }

        // (二)
        // 把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) {// 左边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {// 右边的有序序列还有剩余的元素，就全部填充到temp
            temp[t] = arr[j];
            t++;
            j++;
        }

        // (三)
        // 将temp数组的元素拷贝到arr
        // 注意，并不是每次都拷贝整个temp，而是仅拷贝 left->right 之间的数据，即本次操作的数据
        int tempLeft = left;
        t = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            tempLeft++;
            t++;
        }
    }

}
