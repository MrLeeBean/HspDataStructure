package com.shuai._algorithm.floyd;

import java.util.Arrays;

/**
 * 弗洛伊德算法
 */
public class FloydTest {
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
        graph.floyd();
        graph.showDis();
        graph.showPre();
    }
}

//图
class Graph {

    // 节点（顶点）数据
    public char[] vertex;
    // 保存从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组
    public int[][] dis;
    // 保存到达目标顶点的前驱顶点
    public int[][] pre;

    public Graph(char[] vertex, int[][] dis) {
        this.vertex = vertex;
        this.dis = dis;
        this.pre = new int[vertex.length][vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            // 对pre数组初始化, 注意存放的是前驱顶点的下标
            Arrays.fill(pre[i], i);
        }
    }

    //展示距离表
    public void showDis() {
        System.out.println("\n距离表：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%s\t\t", dis[i][j] == Common.INF ? "INF" : dis[i][j]);
            }
            System.out.println();
        }
    }

    //展示前驱关系表
    public void showPre() {
        System.out.println("\n前驱关系表：");
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex.length; j++) {
                System.out.printf("%s\t\t", pre[i][j] == Common.INF ? "INF" : pre[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * 弗洛伊德算法
     */
    public void floyd() {
        //对中间顶点遍历， k 就是中间顶点的下标 [A, B, C, D, E, F, G]
        for (int k = 0; k < vertex.length; k++) {
            //从i顶点开始出发 [A, B, C, D, E, F, G]
            for (int i = 0; i < vertex.length; i++) {
                //到达j顶点 [A, B, C, D, E, F, G]
                for (int j = 0; j < vertex.length; j++) {
                    // 求出从i 顶点出发，经过 k中间顶点，到达 j 顶点距离
                    int len = dis[i][k] + dis[k][j];
                    if (len < dis[i][j]) {//如果len小于 dis[i][j]
                        //更新距离
                        dis[i][j] = len;
                        //更新前驱顶点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}

class Common {
    // 注意：这里的大数INF最好不要使用Integer.MAX_VALUE
    // 因为如果INF要进行加法运算，会溢出导致出现负权。所以最好设置为一个比较大且不容易相加溢出的数
    public static final int INF = Short.MAX_VALUE;
}
