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
        graph.insertEdge(0, 0, 1);//A-B
        graph.insertEdge(0, 2, 1);//A-C
        graph.insertEdge(1, 2, 1);//B-C
        graph.insertEdge(1, 3, 1);//B-D
        graph.insertEdge(1, 4, 1);//B-E
        //展示邻接矩阵
        graph.showGraph();


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
     * @param v1     第一个顶点的下标，即第几个顶点  "A"-"B" ："A"->0 "B"->1
     * @param v2     第二个顶点的下标，同上
     * @param weight 权：1表示可以连接，0表示不可以连接
     */
    public void insertEdge(int v1, int v2, int weight) {
        //无向图，两个方向都可以连接
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
    }

    /**
     * 展示邻接矩阵
     */
    public void showGraph() {
        for (int[] arr : edges) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
