package com.shuai._algorithm.prim;

import java.util.Arrays;

/**
 * 普利姆算法
 */
public class PrimTest {

    public static void main(String[] args) {

        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000}};

        Graph graph = new Graph(data, weight);
        graph.show();
        graph.prim(0);

    }
}


//图
class Graph {

    public char[] data;//节点（顶点）数据
    public int[][] weight;//存放边，也就是邻接矩阵

    public Graph(char[] data, int[][] weight) {
        if (data != null && data.length > 0) {
            this.data = data;
            this.weight = new int[data.length][data.length];
            if (weight != null && data.length == weight.length && data.length == weight[0].length) {
                this.weight = weight;
            }
        }
    }

    //展示图的邻接矩阵
    public void show() {
        System.out.println();
        if (weight != null) {
            for (int i = 0; i < weight.length; i++) {
                System.out.println(Arrays.toString(weight[i]));
            }
        }
        System.out.println();
    }

    /**
     * 普利姆算法，生成最小生成树
     *
     * @param v 从哪个顶点开始 v取值范围：[0,data.length)
     */
    public void prim(int v) {
        //标记结点(顶点)是否被访问过
        int[] isVisited = new int[data.length];
        //当前这个结点标记为已访问
        isVisited[v] = 1;
        //最小生成树 边数量 = 节点数量-1
        int edgeSize = data.length - 1;
        //遍历
        for (int e = 0; e < edgeSize; e++) {

            //h1 和 h2 记录两个顶点的下标
            int h1 = -1, h2 = -1;
            //记录最小的权，初始成一个大数，后面在遍历过程中，会被替换
            int minW = 10000;

            //这两个双层for循环，是确定每一次生成的子图 ，和哪个结点的距离最近
            //i结点表示被访问过的结点,j结点表示还没有访问过的结点
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    if (isVisited[i] == 1 && isVisited[j] == 0 && weight[i][j] < minW) {
                        //替换minW
                        minW = weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }

            }
            //找到一条边是最小
            System.out.println("边<" + data[h1] + "," + data[h2] + ">" + ",权值为" + weight[h1][h2]);
            //将当前这个结点标记为已经访问
            isVisited[h2] = 1;
        }
    }

}
