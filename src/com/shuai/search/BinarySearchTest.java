package com.shuai.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 */
public class BinarySearchTest {
    public static void main(String[] args) {

        int[] arr1 = {2, 8, 12, 18, 20, 25, 30, 37, 41, 49, 61};
        int result1 = search1(arr1, 0, arr1.length - 1, 8);//查找8
        int result2 = search1(arr1, 0, arr1.length - 1, 9);//查找9
        System.out.println(result1 + " <--> " + result2);

        int[] arr2 = {8, 8, 8, 8, 12, 18, 20, 25, 30, 37, 41, 49, 61, 61};
        List<Integer> resultList1 = search2(arr2, 0, arr2.length - 1, 8);//查找8
        List<Integer> resultList2 = search2(arr2, 0, arr2.length - 1, 61);//查找9
        List<Integer> resultList3 = search2(arr2, 0, arr2.length - 1, 1000);//查找1000
        System.out.println(resultList1 + " <--> " + resultList2 + " <--> " + resultList3);

    }

    /**
     * 二分查找
     *
     * @param arr     待查找数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的数
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int search1(int[] arr, int left, int right, int findVal) {

        // 当 left > right 时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {//向右递归
            return search1(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {//向左递归
            return search1(arr, left, mid - 1, findVal);
        } else {//找到
            return mid;
        }
    }

    /**
     * 二分查找（当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到）
     *
     * @param arr     待查找数组
     * @param left    左边索引
     * @param right   右边索引
     * @param findVal 要查找的数
     * @return 如果找到就返回下标的集合，如果没有找到，就返回空集合
     */
    public static List<Integer> search2(int[] arr, int left, int right, int findVal) {
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if (findVal > midVal) {
            return search2(arr, mid + 1, right, findVal);
        } else if (findVal < midVal) {
            return search2(arr, left, mid - 1, findVal);
        } else {

            // 思路分析
            // 1. 在找到mid索引值，不要马上返回
            // 2. 向mid 索引值的左边扫描，将所有满足值为findVal的元素的下标，加入到集合ArrayList
            // 3. 向mid 索引值的右边扫描，将所有满足值为findVal的元素的下标，加入到集合ArrayList
            // 4. 将ArrayList返回
            List<Integer> resultList = new ArrayList<>();
            int tempIndexLeft = mid - 1;
            while (tempIndexLeft >= 0 && arr[tempIndexLeft] == findVal) {
                resultList.add(tempIndexLeft);
                tempIndexLeft--;
            }
            resultList.add(mid);

            int tempIndexRight = mid + 1;
            while (tempIndexRight <= arr.length - 1 && arr[tempIndexRight] == findVal) {
                resultList.add(tempIndexRight);
                tempIndexRight++;
            }
            return resultList;
        }

    }
}
