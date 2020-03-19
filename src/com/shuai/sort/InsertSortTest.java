package com.shuai.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSortTest {

    public static void main(String[] args) {

//        int[] arr = {45, 12, 37, 26, 31};
//        sort(arr);
//        System.out.println("排序完成：" + Arrays.toString(arr));

        int[] arr = SortUtil.getBigArr();
        SortUtil.showTime();
        sort(arr);
        SortUtil.showTime();
    }

    /**
     * 插入排序
     *
     * @param arr
     */
    public static void sort(int[] arr) {
        int insertValue;//定义待插入的数
        int insertIndex;//定义要插入的位置index
        for (int i = 1; i < arr.length; i++) {
            insertValue = arr[i];
            insertIndex = i - 1;//初始时，要插入的位置为 待插入数前一个数的位置

            // 1. insertIndex >= 0 保证在给insertVal找插入位置，不越界
            // 2. insertVal < arr[insertIndex] 表示还没有找到插入位置
            // 3. 如果满足1和2，就需要将 arr[insertIndex] 后移，同时 insertIndex前移 继续寻找插入位置
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//将 arr[insertIndex] 后移
                insertIndex--;//insertIndex前移
            }
            // 当退出while循环时，说明插入的位置找到, 为：insertIndex + 1
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }
            //System.out.println("第" + i + "趟循环后的数组：" + Arrays.toString(arr));
        }
    }
}
