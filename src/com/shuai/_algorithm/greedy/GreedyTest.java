package com.shuai._algorithm.greedy;

import java.util.*;

/**
 * 贪心算法
 */
public class GreedyTest {

    public static void main(String[] args) {
        greedy();
    }

    /**
     * 电台问题
     */
    public static void greedy() {

        //电台K1
        HashSet<String> k1BroadCast = new HashSet<String>() {
            {
                add("北京");
                add("上海");
                add("天津");
            }
        };
        //电台K2
        HashSet<String> k2BroadCast = new HashSet<String>() {
            {
                add("广州");
                add("北京");
                add("深圳");
            }
        };
        //电台K3
        HashSet<String> k3BroadCast = new HashSet<String>() {
            {
                add("成都");
                add("上海");
                add("杭州");
            }
        };
        //电台K4
        HashSet<String> k4BroadCast = new HashSet<String>() {
            {
                add("上海");
                add("天津");
            }
        };
        //电台K5
        HashSet<String> k5BroadCast = new HashSet<String>() {
            {
                add("杭州");
                add("大连");
            }
        };

        //所有电台HashMap ： key电台名 - value电台可覆盖范围
        HashMap<String, HashSet<String>> allBroadCast = new HashMap<String, HashSet<String>>() {
            {
                put("k1", k1BroadCast);
                put("k2", k2BroadCast);
                put("k3", k3BroadCast);
                put("k4", k4BroadCast);
                put("k5", k5BroadCast);
            }
        };

        //电台要覆盖的所有区域
        HashSet<String> allArea = new HashSet<>();
        for (Map.Entry<String, HashSet<String>> entry : allBroadCast.entrySet()) {
            HashSet<String> value = entry.getValue();
            allArea.addAll(value);
        }

        //记录电台选择的key
        List<String> selectKey = new ArrayList<>();

        while (!allArea.isEmpty()) {

            // 在每次遍历中，定义可覆盖最大区域的电台key
            String maxKey = null;
            // 在每次遍历中，定义电台可覆盖的最大范围
            int maxSize = 0;
            // 内部继续遍历所有电台
            for (Map.Entry<String, HashSet<String>> entry : allBroadCast.entrySet()) {
                //定义一个临时集合，存放遍历过程中的 某电台覆盖的地区 和 当前还没有覆盖的地区 的交集
                HashSet<String> comArea = new HashSet<String>() {
                    {
                        //添加 当前电台可覆盖的所有城市
                        addAll(entry.getValue());
                        //取 当前电台可覆盖的所有城市 和 还没有覆盖地区的 交集
                        retainAll(allArea);
                    }
                };

                if (!comArea.isEmpty()) {
                    // 如果 最大覆盖范围电台还未定义 或者 当前电台覆盖范围大于已知的最大覆盖范围
                    // 则 存储当前电台的key，存储当前电台覆盖的最大范围
                    if (maxKey == null || comArea.size() > maxSize) {
                        maxKey = entry.getKey();
                        maxSize = comArea.size();
                    }
                }
            }

            if (maxKey != null) {
                // 添加当前覆盖范围最大的电台key
                selectKey.add(maxKey);
                // 删除已覆盖的区域，剩余的allArea则为未覆盖的区域
                allArea.removeAll(allBroadCast.get(maxKey));
            }
        }
        System.out.println("电台的选择结果为：" + selectKey);
    }
}
