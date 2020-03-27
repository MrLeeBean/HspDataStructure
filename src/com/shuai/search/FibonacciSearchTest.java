package com.shuai.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 */
public class FibonacciSearchTest {
    public static void main(String[] args) {

        int[] arr1 = {2, 8, 12, 18, 20, 25, 30, 37, 41, 49, 61};
        int result1 = search(arr1, 8);//查找8
        int result2 = search(arr1, 9);//查找9
        System.out.println(result1 + " <--> " + result2);

    }

    public static int maxSize = 20;

    /**
     * 获取斐波那契数列
     * 数列存放于数组中[1, 1, 2, 3, 5, 8, 13, 21, 34, 55...]并将此数组返回
     *
     * @return
     */
    public static int[] getFib() {
        int[] fib = new int[maxSize];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i <= maxSize - 1; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib;
    }

    /**
     * 斐波那契查找算法
     *
     * @param arr 原数组
     * @param key 待查找的关键码（值）
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int search(int[] arr, int key) {

        int low = 0;
        int high = arr.length - 1;

        int k = 0; // 指示斐波那契数列的下标，初始为0是为了根据数组长度确定数组需要扩展的长度
        int[] fib = getFib();// 获取斐波那契数列

        // 获取到斐波那契分割数值的下标，直到 fib[k]-1 >= arr.length 为止
        while (arr.length > fib[k] - 1) {
            k++;
        }
        // 因为 f[k]-1 的值 >= arr的长度 arr.length，因此我们需要构造一个新的数组temp,长度为f[k]-1
        int[] temp = Arrays.copyOf(arr, fib[k] - 1);

        // temp数组中，超出arr长度之外的数 用arr数组最后一个数arr[high]填充
        // 举例:
        // arr = {1,8, 10, 89, 1000, 1234}
        // temp ={1,8, 10, 89, 1000, 1234, 1234, 1234,}
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        // 使用while来循环处理，找到我们的数 key
        int mid;
        int midValue;
        while (low <= high) {

            mid = low + fib[k - 1] - 1;
            midValue = temp[mid];

            if (key < midValue) {//继续向数组的前面查找(左边)
                high = mid - 1;
                /**
                 * 为啥是k--
                 * 1. 全部元素 = 前面的元素 + 后边元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 3. 因为 前面有 f[k-1]个元素,所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                 * 4. 即 在 f[k-1] 的前面继续查找
                 * 5. k-- 即下次循环 mid = f[k-1 -1]-1
                 */
                k--;
            } else if (key > midValue) {//继续向数组的后面查找(右边)
                low = mid + 1;
                /**
                 * 为啥是k-=2
                 * 1. 全部元素 = 前面的元素 + 后边元素
                 * 2. f[k] = f[k-1] + f[k-2]
                 * 3. 因为后面我们有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                 * 4. 即在f[k-2] 的前面进行查找
                 * 5. k-=2 即下次循环 mid = f[k-1 - 2] - 1
                 */
                k -= 2;
            } else {
                if (mid > high) {
                    return high;//查找值的下标在arr数组额范围内，直接返回位置mid
                } else {
                    return mid;//不在就返回位置high,为什么？因为后面几位的值和right位置的值是一样的，说明查找的位置就是right
                }
            }
        }
        return -1;

    }
}
