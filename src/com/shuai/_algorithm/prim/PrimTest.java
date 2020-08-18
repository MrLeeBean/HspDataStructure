package com.shuai._algorithm.prim;

/**
 * 普里姆算法
 */
public class PrimTest {

    public static void main(String[] args) {

        //顶点
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        //注意：这里INF是一个大数，表示不连通
        int INF = Common.INF;

        int[][] matrix = new int[][]{
                {INF, 5, 7, INF, INF, INF, 2},
                {5, INF, INF, 9, INF, INF, 3},
                {7, INF, INF, INF, 8, INF, INF},
                {INF, 9, INF, INF, INF, 4, INF},
                {INF, INF, 8, INF, INF, 5, 4},
                {INF, INF, INF, 4, 5, INF, 6},
                {2, 3, INF, INF, 4, 6, INF}
        };

        Graph graph = new Graph(vertex, matrix);
        graph.show();
        graph.prim(0);

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
     * 普里姆算法，生成最小生成树
     *
     * @param v 从哪个顶点开始 v取值范围：[0,data.length)
     */
    public void prim(int v) {
        //标记结点(顶点)是否被访问过
        int[] isVisited = new int[vertex.length];
        //当前这个结点标记为已访问
        isVisited[v] = 1;
        //最小生成树 边数量 = 节点数量-1
        int edgeSize = vertex.length - 1;
        //遍历
        for (int e = 0; e < edgeSize; e++) {

            //h1 和 h2 记录两个顶点的下标
            int h1 = -1, h2 = -1;
            //记录最小的权，初始成一个大数，后面在遍历过程中，会被替换
            int minW = Common.INF;

            //这两个双层for循环，是确定每一次生成的子图 ，和哪个结点的距离最近
            //i结点表示被访问过的结点,j结点表示还没有访问过的结点
            for (int i = 0; i < vertex.length; i++) {
                for (int j = 0; j < vertex.length; j++) {
                    if (isVisited[i] == 1 && isVisited[j] == 0 && matrix[i][j] < minW) {
                        //替换minW
                        minW = matrix[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }

            }
            //找到一条边是最小
            System.out.println("边<" + vertex[h1] + "," + vertex[h2] + ">" + ",权值为" + matrix[h1][h2]);
            //将当前这个结点标记为已经访问
            isVisited[h2] = 1;
        }
    }

}

class Common {
    // 注意：这里的大数INF最好不要使用Integer.MAX_VALUE
    // 因为如果INF要进行加法运算，会溢出导致出现负权。所以最好设置为一个比较大且不容易相加溢出的数
    public static final int INF = Short.MAX_VALUE;
}
