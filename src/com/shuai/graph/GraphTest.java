package com.shuai.graph;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 图
 */
public class GraphTest {
    public static void main(String[] args) {

        //顶点
        String[] vertexArr = {"A", "B", "C", "D", "E"};
        //创建图对象
        Graph graph = new Graph(vertexArr.length);
        //添加顶点
        for (String s : vertexArr) {
            graph.insertVertex(s);
        }
        //添加边
        graph.insertEdge(0, 1, 1);//A-B
        graph.insertEdge(0, 2, 1);//A-C
        graph.insertEdge(1, 2, 1);//B-C
        graph.insertEdge(1, 3, 1);//B-D
        graph.insertEdge(1, 4, 1);//B-E

        //展示邻接矩阵
        System.out.println("\n展示邻接矩阵：");
        graph.showGraph();

        System.out.println("\n深度优先遍历：");
        //深度优先遍历
        graph.dfs();

    }
}

class Graph {

    //存储顶点集合
    private ArrayList<String> vertexList;
    //存储图对应的邻结矩阵
    private int[][] edges;

    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
    }

    /**
     * 添加顶点
     *
     * @param vertex 顶点的值
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     *
     * @param index1 第一个顶点的下标，即第几个顶点  "A"-"B" ："A"->0 "B"->1
     * @param index2 第二个顶点的下标，同上
     * @param weight 权：1表示可以连接，0表示不可以连接
     */
    public void insertEdge(int index1, int index2, int weight) {
        //无向图，两个方向都可以连接
        edges[index1][index2] = weight;
        edges[index2][index1] = weight;
    }

    /**
     * 展示邻接矩阵
     */
    public void showGraph() {
        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }
    }

    //////////////////////////////////////////////////////
    //
    //              深度优先遍历(DFS)
    //
    /////////////////////////////////////////////////////


    //定义给数组boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    /**
     * 获取以下标为index的节点开始的第一个邻接节点
     *
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 下标为index的节点的 某个邻接节点的下标 为neighborIndex
     * 那么此方法用于获取index节点继neighborIndex邻接节点之后的的下一个邻接节点
     *
     * @param index
     * @param neighborIndex
     * @return
     */
    public int getNextNeighbor(int index, int neighborIndex) {
        for (int i = neighborIndex + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 深度优先遍历
     */
    public void dfs() {
        isVisited = new boolean[vertexList.size()];
        //遍历所有的结点，进行dfs
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    private void dfs(boolean[] isVisited, int index) {
        //输出当前节点
        System.out.print(vertexList.get(index) + "->");
        //将当前节点设置为已经访问
        isVisited[index] = true;
        //获取当前节点的第一个邻接节点
        int neighbor = getFirstNeighbor(index);
        while (neighbor != -1) {//如果邻接节点neighbor存在
            if (!isVisited[neighbor]) {//如果邻接节点neighbor没有被访问过，则递归
                dfs(isVisited, neighbor);
            }
            //neighbor访问过，则获取index的下一个邻接节点
            neighbor = getNextNeighbor(index, neighbor);
        }
    }
}
