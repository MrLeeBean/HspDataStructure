package com.shuai._algorithm.kruskal;

import java.util.*;

/**
 * 克鲁斯卡尔算法
 */
public class KruskalTest {
    public static void main(String[] args) {

        //顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //注意：这里INF是一个大数，表示不连通
        int INF = Common.INF;
        //克鲁斯卡尔算法的邻接矩阵
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, INF, INF, INF, 16, 14},
                /*B*/ {12, 0, 10, INF, INF, 7, INF},
                /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, INF, 2, 0, 9},
                /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        Graph graph = new Graph(vertex, matrix);

        System.out.println("\n邻接矩阵为：\n");
        graph.show();

        System.out.println("\n所有的边为(已排序)：\n");
        System.out.println(graph.getEdgesAndSortByWeight());

        graph.kruskal();

    }
}

//图
class Graph {

    public char[] vertex;//节点（顶点）数据
    public int[][] matrix;//存放边，也就是邻接矩阵

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    //展示图的邻接矩阵
    public void show() {
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%s\t\t", matrix[i][j] == Common.INF ? "INF" : matrix[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 获取所有可连通的边，并根据weight排序
     */
    public List<Edge> getEdgesAndSortByWeight() {
        List<Edge> list = new ArrayList<Edge>();
        //获取所有可连通的边
        for (int i = 0; i < vertex.length; i++) {
            for (int j = i + 1; j < vertex.length; j++) {
                if (matrix[i][j] != Common.INF) {
                    list.add(new Edge(vertex[i], vertex[j], matrix[i][j]));
                }
            }
        }
        //排序
        list.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        return list;
    }


    /**
     * 获取顶点数据的index位置
     *
     * @param c 顶点的值，比如'A','B'
     * @return 返回c顶点对应的下标，如果找不到，返回-1
     */
    public int getVertexIndex(char c) {
        for (int i = 0; i < vertex.length; i++) {
            if (c == vertex[i]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 获取下标为i的顶点的终点(), 用于后面判断两个顶点的终点是否相同
     *
     * @param ends ： 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i    : 表示传入的顶点对应的下标
     * @return 返回的就是 下标为i的这个顶点对应的终点的下标
     */
    public int getEndVertexIndex(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }


    /**
     * 克鲁斯卡尔算法
     */
    public void kruskal() {

        //存放所有的边
        List<Edge> edges = getEdgesAndSortByWeight();

        //保存结果的边，保存最后的最小生成树
        List<Edge> resultEdge = new ArrayList();

        //保存"已有最小生成树" 中的每个顶点在最小生成树中的终点
        int[] ends = new int[edges.size()];

        //遍历edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入resultEdge, 否则不能加入
        for (int i = 0; i < edges.size(); i++) {
            //获取到第i条边的第一个顶点(首)
            int i1 = getVertexIndex(edges.get(i).start);
            //获取到第i条边的第2个顶点(尾)
            int i2 = getVertexIndex(edges.get(i).end);

            //获取i1这个顶点在已有最小生成树中的终点
            int e1 = getEndVertexIndex(ends, i1);
            //获取i2这个顶点在已有最小生成树中的终点
            int e2 = getEndVertexIndex(ends, i2);
            //是否构成回路
            if (e1 != e2) {//没有构成回路
                // 设置 e1 在"已有最小生成树"中的终点
                ends[e1] = e2;
                // 有一条边加入到resultEdge数组
                resultEdge.add(edges.get(i));
            }
        }

        System.out.println("\n最小生成树为：\n");
        //统计并打印 "最小生成树", 输出resultEdge
        for (Edge edge : resultEdge) {
            System.out.println(edge);
        }
    }

}

//边
class Edge {
    //边的一个点（首）
    public char start;
    //边的另一个点（尾）
    public char end;
    //边的权值
    public int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "<" + start + "," + end + ">" + "=" + weight;
    }
}

class Common {
    // 注意：这里的大数INF最好不要使用Integer.MAX_VALUE
    // 因为如果INF要进行加法运算，会溢出导致出现负权。所以最好设置为一个比较大且不容易相加溢出的数
    public static final int INF = Short.MAX_VALUE;
}

