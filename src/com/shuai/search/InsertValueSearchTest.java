package com.shuai.search;

/**
 * 差值查找
 */
public class InsertValueSearchTest {
    public static void main(String[] args) {
        int[] arr1 = {2, 8, 12, 18, 20, 25, 30, 37, 41, 49, 61};
        int result1 = search1(arr1, 0, arr1.length - 1, 8);//查找8
        int result2 = search1(arr1, 0, arr1.length - 1, 9);//查找9
        int result3 = search1(arr1, 0, arr1.length - 1, 100000);//查找9
        System.out.println(result1 + " <--> " + result2 + " <--> " + result3);
    }

    /**
     * 差值查找
     *
     * @param arr     待查找数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的数
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int search1(int[] arr, int left, int right, int findVal) {

        // 当 left > right 时，说明递归整个数组，但是没有找到
        // 注意：findVal < arr[left]  和  findVal > arr[right] 必须需要,否则我们得到mid以后，arr[mid]可能越界
        if (left > right || findVal < arr[left] || findVal > arr[right]) {
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return search1(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return search1(arr, left, mid - 1, findVal);
        } else {//找到
            return mid;
        }
    }
}
