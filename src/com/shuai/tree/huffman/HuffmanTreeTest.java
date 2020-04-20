package com.shuai.tree.huffman;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼树
 */
public class HuffmanTreeTest {
    public static void main(String[] args) {

        int[] arr = {9, 12, 6, 3, 5, 15};
        Node rootNode = createHuffmanTree(arr);
        rootNode.preOrder();

    }

    /**
     * 构建一棵赫夫曼树
     *
     * @param arr
     * @return
     */
    public static Node createHuffmanTree(int[] arr) {

        ArrayList<Node> nodes = new ArrayList<>();
        for (int item : arr) {
            nodes.add(new Node(item));
        }

        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes);
            //(1) 取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //(2) 取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
            //(3) 构建一颗新的二叉树
            Node parentNode = new Node(leftNode.value + rightNode.value);
            parentNode.left = leftNode;
            parentNode.right = rightNode;
            //(4) 从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //(5) 将parentNode加入到nodes
            nodes.add(parentNode);
        }
        //返回哈夫曼树的root结点
        return nodes.get(0);

    }
}

// 为了让Node 对象持续排序Collections集合排序，让Node 实现Comparable接口，
class Node implements Comparable<Node> {
    public int value;// 结点权值
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        //以Node的value值的大小作为 判断Node大小的依据
        return this.value - o.value;
    }
}