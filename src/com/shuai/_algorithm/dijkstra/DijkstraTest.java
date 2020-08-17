package com.shuai._algorithm.dijkstra;

import java.util.Arrays;

/**
 * 迪杰斯特拉算法解决最短路径问题
 */
public class DijkstraTest {
    public static void main(String[] args) {
        //顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        int N = Common.INF;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);
        graph.show();
        graph.dijkstra(6);
        graph.showDjs();

    }

}

//图
class Graph {
    public char[] data;//节点（顶点）数据
    public int[][] matrix;//存放边，也就是邻接矩阵

    public Graph(char[] data, int[][] matrix) {
        this.data = data;
        this.matrix = matrix;
    }

    //展示图的邻接矩阵
    public void show() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                System.out.printf("%s\t\t", matrix[i][j] == Common.INF ? "INF" : matrix[i][j]);
            }
            System.out.println();
        }
    }


    public void showDjs() {
        for (int i = 0; i < data.length; i++) {

            System.out.println("到" + data[i] + "距离为" + vh.distanceToVertex[i] + ",前驱节点为" + vh.previousVertex[i]);

        }
    }

    //顶点帮助类
    private VertexHelper vh;

    public void dijkstra(int start) {
        vh = new VertexHelper(data.length, start);
        //更新index下标的顶点到周围顶点的距离 和 周围顶点的前驱顶点,
        update(start);
        //继续更新其他顶点
        for (int i = 1; i < data.length; i++) {
            // 选择并返回新的访问顶点
            int index = updateNewStart();
            // 更新
            update(index);
        }
    }


    /**
     * 继续选择并返回新的访问顶点
     * 比如这里的G 完后，就是 A点作为新的访问顶点(注意不是出发顶点)
     *
     * @return
     */
    private int updateNewStart() {
        int min = Common.INF;
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            if (!vh.visitedVertex[i] && vh.distanceToVertex[i] < min) {
                min = vh.distanceToVertex[i];
                index = i;
            }
        }
        vh.visitedVertex[index] = true;
        return index;
    }

    /**
     * 更新index下标的顶点到周围顶点的距离 和 周围顶点的前驱顶点
     *
     * @param start
     */
    private void update(int start) {
        int len = 0;
        //遍历所有顶点（i取值为：0 ~ data.length-1）
        for (int i = 0; i < data.length; i++) {
            // len 含义是 : 出发顶点到start的距离 + 从start顶点到i顶点的距离的和
            len = vh.distanceToVertex[start] + matrix[start][i];
            // 如果i顶点没有被访问过，并且 len 小于已经记录的出发顶点到j顶点的距离，就需要更新
            if (!vh.visitedVertex[i] && len < vh.distanceToVertex[i]) {
                //更新出发顶点到i顶点的距离
                vh.distanceToVertex[i] = len;
                //更新i顶点的前驱为start顶点
                vh.previousVertex[i] = start;
            }
        }
    }

}

//顶点帮助类
class VertexHelper {
    // 记录各个顶点（按照下标记录）是否访问过，会动态更新
    public boolean[] visitedVertex;
    // 记录各个顶点（按照下标记录）对应的前一个顶点（前驱）下标, 会动态更新
    public int[] previousVertex;
    // 记录出发顶点到其他所有顶点（按照下标记录）的距离。会动态更新，求的最短距离就会存放到此
    public int[] distanceToVertex;

    /**
     * 构造器
     *
     * @param vertexLength：顶点个数
     * @param startIndex：出发顶点对应的下标, 比如G顶点，下标就是6
     */
    public VertexHelper(int vertexLength, int startIndex) {
        this.visitedVertex = new boolean[vertexLength];
        this.previousVertex = new int[vertexLength];
        this.distanceToVertex = new int[vertexLength];

        Arrays.fill(visitedVertex, false);
        Arrays.fill(previousVertex, 0);
        Arrays.fill(distanceToVertex, Common.INF);

        // 设置出发顶点被访问过
        visitedVertex[startIndex] = true;
        // 设置出发顶点的访问距离为0
        distanceToVertex[startIndex] = 0;
    }
}


class Common {
    //注意：这里的大数INF不能用Integer.MAX_VALUE，因为在update方法中，INF要进行加法运算，会越界。
    public static final int INF = Short.MAX_VALUE;
}

