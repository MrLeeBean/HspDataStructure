package com.shuai.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSortTest {
    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        sort2(arr);
//        System.out.println("排序完成：" + Arrays.toString(arr));

        int[] arr = SortUtil.getBigArr();
        SortUtil.showTime2();
        sort2(arr);
        SortUtil.showTime2();
    }

    /**
     * 希尔排序时，对有序序列在插入时采用交换法。
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int count = 0;
        int temp;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) { //基数
            for (int i = gap; i < arr.length; i++) {

                //交换法
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
            //System.out.println("第" + ++count + "趟循环后的数组：" + Arrays.toString(arr));
        }
    }

    /**
     * 优化方案：
     * 希尔排序时，对有序序列在插入时采用位移法。
     *
     * @param arr
     */
    public static void sort2(int[] arr) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {

                //位移法
                int insertIndex = i;
                int insertValue = arr[insertIndex];

                while (insertIndex - gap >= 0 && insertValue < arr[insertIndex - gap]) {
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                if (insertIndex != i) {
                    arr[insertIndex] = insertValue;
                }
            }
            //System.out.println("第" + ++count + "趟循环后的数组：" + Arrays.toString(arr));
        }
    }

    /**
     * 步骤分解希尔排序（交换法）
     *
     * @param arr
     */
    public static void sortStep(int[] arr) {

        // 希尔排序的第1轮排序
        // 第1轮排序，是将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素), 步长5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的那个元素，则交换
                if (arr[j] > arr[j + 5]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println("希尔排序1轮后 = " + Arrays.toString(arr));


        // 希尔排序的第2轮排序
        // 第2轮排序，是将10个数据分成了 5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素), 步长2
            for (int j = i - 2; j >= 0; j -= 2) {
                // 如果当前元素大于加上步长后的那个元素，则交换
                if (arr[j] > arr[j + 2]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }
        System.out.println("希尔排序2轮后 = " + Arrays.toString(arr));


        // 希尔排序的第3轮排序
        // 第3轮排序，是将10个数据分成了 2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            // 遍历各组中所有的元素(共5组，每组有2个元素), 步长1
            for (int j = i - 1; j >= 0; j -= 1) {
                // 如果当前元素大于加上步长后的那个元素，则交换
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("希尔排序3轮后 = " + Arrays.toString(arr));
    }
}
