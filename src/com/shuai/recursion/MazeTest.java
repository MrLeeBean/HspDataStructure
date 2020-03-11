package com.shuai.recursion;

/**
 * 迷宫问题
 */
public class MazeTest {

    public static void main(String[] args) {

        int[][] map = new int[8][7];

        // 1 表示墙

        //两边列堵墙
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //两边行堵墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //中间部分位置堵墙
        map[3][1] = 1;
        map[3][2] = 1;

        printMap("原始地图", map);

        printMap("回溯地图", map);

    }

    /**
     * 打印地图
     *
     * @param map
     */
    public static void printMap(String title, int[][] map) {
        System.out.println("-------" + title + "------");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + "  ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯来给小球找路
     * <p>
     * 说明
     * 1. map 表示地图
     * 2. i,j 表示从地图的哪个位置开始出发 (1,1)
     * 3. 如果小球能到 map[6][5] 位置，则说明通路找到.
     * 4. 约定： 当map[i][j] 为 0 表示该点没有走过；当为 1 表示墙 ； 2 表示通路可以走 ； 3 表示该点已经走过，但是走不通
     * 5. 在走迷宫时，需要确定一个策略(方法) 下->右->上->左 , 如果该点走不通，再回溯
     *
     * @param map 表示地图
     * @param i   从哪个位置开始出发寻找.i表示第几行，j表示第几列
     * @param j   从哪个位置开始出发寻找.i表示第几行，j表示第几列
     * @return 如果找到通路，就返回true, 否则返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {// 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                // 假定该点是可以走通.
                map[i][j] = 2;
                //按照策略 下->右->上->左  走
                if (setWay(map, i + 1, j)) {//下
                    return true;
                } else if (setWay(map, i, j + 1)) {//右
                    return true;
                } else if (setWay(map, i - 1, j)) {//上
                    return true;
                } else if (setWay(map, i, j - 1)) {//左
                    return true;
                } else {
                    //说明该点走过但走不通，是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {
                // 如果map[i][j] != 0 , 可能是 1（墙 可以走）， 2（走过的 不可以走）， 3（走过但走不通 不可以走）
                // 总之两个原则：①墙不可以走、②走过的不可以走
                return false;
            }
        }

    }

}
