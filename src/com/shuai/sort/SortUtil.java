package com.shuai.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 排序工具类
 */
public class SortUtil {

    /**
     * 展示当前时间
     *
     * @return
     */
    public static void showTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println(dateStr);
    }

    /**
     * 创建一个包含80000条数据的数组
     *
     * @return
     */
    public static int[] getBigArr() {
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000); //生成一个[0, 8000000) 数
        }
        return arr;
    }
}
