package com.shuai.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSortTest {
    public static void main(String[] args) {

//        int[] arr = {542, 53, 3, 14, 214, 748};
//        sort(arr);
//        System.out.println("排序完成：" + Arrays.toString(arr));

        SortUtil.showTime2();
        int[] arr = SortUtil.getBigArr();
        sort(arr);
        SortUtil.showTime2();

    }

    /**
     * 基数排序
     *
     * @param arr
     */
    public static void sort(int[] arr) {

        // 得到数组中最大的数
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到数组中最大的数 是几位数
        int maxLength = (max + "").length();

        // 定义一个二维数组，表示10个桶, 每个桶就是一个一维数组
        // 说明
        // 1. 二维数组包含10个一维数组，每个一维数组表示一个桶。
        // 2. 一个桶（一位数组）中，最多可以放入arr.length个数据。所以为了防止在放入数的时候数据溢出，则每个一维数组(桶)，大小定为arr.length
        // 3. 由此可见，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 为了记录每个桶中，实际存放了多少个数据，我们新定义一个一维数组来记录各个桶在每次循环中放入的数据个数
        // 比如：bucketElementCounts[0] , 记录的就是 bucket[0] 桶的放入数据个数
        int[] bucketElementCounts = new int[10];

        // 个位、十位、百位...依次循环
        // 针对每个元素的对应位进行排序处理，第一次是个位，第二次是十位，第三次是百位..
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {

            //【存数据】
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素的对应位的值
                int digitOfElement = (arr[j] / n) % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                // 对应桶中存放的数据个数+1
                bucketElementCounts[digitOfElement]++;
            }

            //【取数据】
            // 按照这个桶的顺序，取出数据并放入原来数组
            int index = 0;
            // 遍历每一个桶，并将桶中数据，放入到原数组
            for (int x = 0; x < bucket.length; x++) {
                // 如果桶中有数据，我们才放入到原数组
                if (bucketElementCounts[x] != 0) {
                    // 遍历第x个桶（即bucket中第x个数组），第x个桶中存放了bucketElementCounts[x]个数据,
                    for (int y = 0; y < bucketElementCounts[x]; y++) {
                        // 将这些数据，依次放入arr数组中
                        arr[index] = bucket[x][y];
                        index++;
                    }

                }
                //注意：每次遍历完每个桶后，需要将当前桶中存放的数据个数：bucketElementCounts[x] = 0 做清零处理。
                bucketElementCounts[x] = 0;
            }

            //System.out.println("第" + (i + 1) + "趟循环后的数组：" + Arrays.toString(arr));
        }

    }
}
