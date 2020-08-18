package com.shuai._algorithm.dijkstra;

/**
 * 迪杰斯特拉算法解决最短路径问题
 */
public class DijkstraTest {
    public static void main(String[] args) {
        //顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int N = Common.INF;
        //邻接矩阵
        int[][] matrix = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {0, 12, N, N, N, 16, 14},
                /*B*/ {12, 0, 10, N, N, 7, N},
                /*C*/ {N, 10, 0, 3, 5, 6, N},
                /*D*/ {N, N, 3, 0, 4, N, N},
                /*E*/ {N, N, 5, 4, 0, 2, 8},
                /*F*/ {16, 7, 6, N, 2, 0, 9},
                /*G*/ {14, N, N, N, 8, 9, 0}
        };

        Graph graph = new Graph(vertex, matrix);
        graph.show();
        graph.dijkstra(3);

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
     * 迪杰斯特拉算法
     *
     * @param vs 起始顶点(start vertex)。即计算"顶点vs"到其它顶点的最短路径。
     */
    public void dijkstra(int vs) {
        // flag[i]=true表示"顶点vs"到"顶点i"的最短路径已成功获取.
        // true顶点位于[集合S]中，false顶点位于[集合U]中
        boolean[] flag = new boolean[vertex.length];
        // 前驱顶点数组。即，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
        int[] prev = new int[vertex.length];
        // 长度数组。即，dist[i]是"顶点vs"到"顶点i"的最短路径的长度。
        int[] dist = new int[vertex.length];

        for (int i = 0; i < vertex.length; i++) {
            flag[i] = false;
            prev[i] = 0;
            dist[i] = matrix[vs][i];
        }

        // 对"顶点vs"自身进行初始化，集合S中最初只有vs一个顶点
        flag[vs] = true;
        dist[vs] = 0;

        // 遍历vertex.length-1次；每次找出一个顶点的最短路径。
        for (int i = 1; i < vertex.length; i++) {
            // 寻找当前最小的路径；
            // 即，在未获取最短路径的顶点中（集合U中），找到离vs最近的顶点(k)。
            int min = Common.INF;
            int k = 0;
            for (int j = 0; j < vertex.length; j++) {
                if (!flag[j] && dist[j] < min) {
                    min = dist[j];
                    k = j;
                }
            }
            // 标记"顶点k"为已经获取到最短路径 ==> 顶点k加入到S集合中
            flag[k] = true;
            // 存储"顶点k"的最短路径为min
            dist[k] = min;

            // 修正当前最短路径和前驱顶点  ==> 修正U集合中各个顶点到起始点的距离
            // 即，当已知"顶点k的最短路径"之后，更新 "未获取最短路径的顶点（集合U中顶点）的最短路径 和 前驱顶点"。
            for (int j = 0; j < vertex.length; j++) {
                // dist[k] + matrix[k][j]:k到起始点的最短路径+j到k的路径
                int len = matrix[k][j] == Common.INF ? Common.INF : dist[k] + matrix[k][j];
                // 如果j没有成功获取到最短路径（属于集合U），且 k到起始点的最短路径+j到k的路径 < j到起始点已知的最短路径
                // 则更新j的最短路径、更新j的前驱节点
                // 注意：集合U（flag[j] = false）中的顶点的最短路径dist[j]还未真正确认下来，所以需要不断调整。
                // 只有进入集合S中（flag[j] = true）中的顶点，最短路径才最终确认了下来。
                if (!flag[j] && len < dist[j]) {
                    dist[j] = len;
                    prev[j] = k;
                }
            }

        }
        // 打印dijkstra最短路径的结果
        System.out.printf("\nDijkstra-起始点(%c): \n", vertex[vs]);
        for (int i = 0; i < vertex.length; i++) {
            System.out.printf("  最短路径(%c, %c)=%d\n", vertex[vs], vertex[i], dist[i]);
        }
    }

}


class Common {
    // 注意：这里的大数INF最好不要使用Integer.MAX_VALUE
    // 因为如果INF要进行加法运算，会溢出导致出现负权。所以最好设置为一个比较大且不容易相加溢出的数
    public static final int INF = Short.MAX_VALUE;
}

