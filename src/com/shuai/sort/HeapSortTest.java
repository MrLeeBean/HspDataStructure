package com.shuai.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSortTest {

    public static void main(String[] args) {

        int[] arr = {4, 6, 8, 5, 9};
        sort(arr);
        System.out.println("排序完成：" + Arrays.toString(arr));

//        int[] arr = SortUtil.getBigArr();
//        SortUtil.showTime2();
//        sort(arr);
//        SortUtil.showTime2();
//        System.out.println("排序完成：" + Arrays.toString(arr));

    }

    /**
     * 堆排序
     *
     * @param arr 待排序数组
     */
    public static void sort(int[] arr) {

        //1.构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从最后一个非叶子结点从下至上，从右至左调整结构
            //最后一个非叶子节点索引为 arr.length / 2 - 1，倒数第二个非叶子节点索引则-1，以此类推
            adjustHeap1(arr, i, arr.length);
        }

        //2.交换堆顶元素与末尾元素 + 调整堆结构
        for (int i = arr.length - 1; i >= 0; i--) {
            //将堆顶元素与末尾元素进行交换
            swap(arr, 0, i);
            //重新对堆进行调整
            adjustHeap1(arr, 0, i);
        }
    }

    /**
     * 调整大顶堆
     *
     * @param arr    待调整的数组
     * @param i      调整：把以i（非叶子节点）为父节点的树，调整为大顶堆
     * @param length 对多少个元素进行调整，在将最大元素"沉"到数组末端的过程中，此值逐渐减少
     */
    public static void adjustHeap1(int[] arr, int i, int length) {
        //从i结点的左子结点开始，也就是 k = 2i+1 处开始
        //下一个位置为k的左子节点，也就是 k = 2*k+1 处
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            //如果子节点值 > 父节点值
            if (arr[k] > arr[i]) {
                swap(arr, k, i);//交换位置
                i = k;//切换i的位置为子节点，将i指向k,继续循环比较和调整
            } else {
                break;
            }
        }
    }

    public static void adjustHeap2(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    /**
     * 交换数组中的两个元素
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
