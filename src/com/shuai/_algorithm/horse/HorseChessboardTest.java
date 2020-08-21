package com.shuai._algorithm.horse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 马踏棋盘算法
 */
public class HorseChessboardTest {

    // 棋盘为：CHESS_X * CHESS_Y
    // 则棋盘最大坐标为：CHESS_X-1 * CHESS_Y-1
    private static int CHESS_X = 8;
    private static int CHESS_Y = 8;
    //棋盘
    private static int[][] chess = new int[CHESS_X][CHESS_Y];
    //标记棋盘的各个位置是否被访问过
    private static boolean[][] isVisited = new boolean[CHESS_X][CHESS_Y];
    //标记是否棋盘的所有位置都被访问，true表示成功
    private static boolean isFinished = false;

    public static void main(String[] args) {

        //马踏棋盘算法
        horseTravel(chess, isVisited, 0, 0, 1);

        //打印结果
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 马踏棋盘算法
     *
     * @param chess 棋盘
     * @param x     当前点的x坐标
     * @param y     当前点的y坐标
     * @param step  是第几步 ,初始位置就是第1步
     */
    public static void horseTravel(int[][] chess, boolean[][] isVisited, int x, int y, int step) {
        //设置当前位置为 第step步
        chess[x][y] = step;
        //设置当前位置为 已访问
        isVisited[x][y] = true;
        //获取当前位置下一个可走的位置点
        List<Point> nextList = next(new Point(x, y));

        //贪心算法优化：对nextList进行排序,排序的规则就是对nextList的所有的Point对象的下一步的位置的数目，进行非递减排序
        sort(nextList);
        //遍历nextList
        while (!nextList.isEmpty()) {
            //取出下一个可以走的位置
            Point p = nextList.remove(0);
            if (!isVisited[p.x][p.y]) {//判断该点是否已经访问过，如果没有访问过，则继续走，同时step+1
                horseTravel(chess, isVisited, p.x, p.y, step + 1);
            }
        }
        //判断马儿是否完成了任务，使用step和应该走的步数CHESS_X * CHESS_Y作比较
        //如果 step < HESS_X * CHESS_Y 并且!isFinished，说明当前处于一个回溯的过程中。

        if (step < CHESS_X * CHESS_Y && !isFinished) {
            //则：取消当前位置的step步
            chess[x][y] = 0;
            //则：将当前位置设置为未访问
            isVisited[x][y] = false;
        } else {
            isFinished = true;
        }
    }

    /**
     * 根据当前的点，查找马儿下一个可走的位置点
     *
     * @param curPoint
     * @return
     */
    public static List<Point> next(Point curPoint) {
        List<Point> nextList = new ArrayList<>();
        int x = curPoint.x;//当前点的x坐标
        int y = curPoint.y;//当前点的y坐标

        //0
        if (x + 2 <= CHESS_X - 1 && y - 1 >= 0) {
            nextList.add(new Point(x + 2, y - 1));
        }
        //1
        if (x + 2 <= CHESS_X - 1 && y + 1 <= CHESS_Y - 1) {
            nextList.add(new Point(x + 2, y + 1));
        }
        //2
        if (x + 1 <= CHESS_X - 1 && y + 2 <= CHESS_Y - 1) {
            nextList.add(new Point(x + 1, y + 2));
        }
        //3
        if (x - 1 >= 0 && y + 2 <= CHESS_Y - 1) {
            nextList.add(new Point(x - 1, y + 2));
        }
        //4
        if (x - 2 >= 0 && y + 1 <= CHESS_Y - 1) {
            nextList.add(new Point(x - 2, y + 1));
        }
        //5
        if (x - 2 >= 0 && y - 1 >= 0) {
            nextList.add(new Point(x - 2, y - 1));
        }
        //6
        if (x - 1 >= 0 && y - 2 >= 0) {
            nextList.add(new Point(x - 1, y - 2));
        }
        //7
        if (x + 1 < CHESS_X - 1 && y - 2 >= 0) {
            nextList.add(new Point(x + 1, y - 2));
        }

        return nextList;

    }

    //根据当前位置 对所有下一步的位置 的 下一个nextList的size，进行非递减排序, 以减少回溯的次数
    public static void sort(List<Point> nextList) {
        nextList.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return next(o1).size() - next(o2).size();
            }
        });
    }
}

//棋盘上点的坐标
class Point {
    public int x;//横坐标
    public int y;//纵坐标

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
