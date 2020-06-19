package com.shuai._algorithm.binarysearch;

/**
 * 二分查找非递归的实现
 */
public class BinarySearch {
    public static void main(String[] args) {

        int[] arr = {2, 5, 34, 67, 222, 1234};

        int index1 = searchByRecursion(arr, 0, arr.length - 1, 1234);
        System.out.println("递归查找：" + index1);

        int index2 = searchByLoop(arr, 1234);
        System.out.println("循环查找：" + index2);

    }

    /**
     * 二分查找-->通过递归方式
     *
     * @param arr     待查找的数组, arr是升序排序
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 需要查找的数
     * @return 返回查找结果对应的下标，-1表示没有找到
     */
    public static int searchByRecursion(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (midVal == findVal) {
            return mid;
        } else if (midVal < findVal) {
            return searchByRecursion(arr, mid + 1, right, findVal);
        } else {
            return searchByRecursion(arr, left, mid - 1, findVal);
        }
    }

    /**
     * 二分查找-->通过循环方式（非递归）
     *
     * @param arr     待查找的数组, arr是升序排序
     * @param findVal 需要查找的数
     * @return 返回查找结果对应的下标，-1表示没有找到
     */
    public static int searchByLoop(int[] arr, int findVal) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = arr[mid];
            if (midVal == findVal) {
                return mid;
            } else if (midVal < findVal) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}