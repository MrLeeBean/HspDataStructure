package com.shuai.tree.huffman.huffmancode;

import java.util.*;

/**
 * 赫夫曼编码
 */
public class HuffmanCodeTest {
    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        List<Node> nodes = getNodes(content.getBytes());

        //遍历List<Node>
        for (Node node : nodes) {
            System.out.println("Node信息 => " + "数据：" + (char) node.data.byteValue() + "，权：" + node.weight);
        }

        //创建赫夫曼树
        Node rootNode = createHuffmanTree(nodes);

        System.out.println("\n赫夫曼树遍历结果：");
        //前序遍历赫夫曼树
        rootNode.preOrder();
    }

    /**
     * 将字节数组转换为List<Node>
     *
     * @param bytes 接收字节数组
     * @return 返回 List<Node>
     */
    public static List<Node> getNodes(byte[] bytes) {
        List<Node> list = new ArrayList<>();
        // 通过map来存储不同字符 和 字符出现的次数。
        // key表示字符，value表示字符出现的次数。
        Map<Byte, Integer> map = new HashMap<>();
        for (Byte key : bytes) {
            if (map.containsKey(key)) {
                Integer value = map.get(key);
                map.put(key, ++value);
            } else {
                map.put(key, 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            list.add(new Node(entry.getKey(), entry.getValue()));
        }
        return list;
    }

    /**
     * 通过List<Node> 创建对应的赫夫曼树
     *
     * @param nodes
     * @return
     */
    public static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parentNode = new Node(null, leftNode.weight + rightNode.weight);//根节点 没有data, 只有权值

            parentNode.left = leftNode;
            parentNode.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parentNode);

        }
        return nodes.get(0);
    }


    /**
     * 遍历赫夫曼树
     *
     * @param root
     */
    public static void preOrder(Node root) {
        if (root == null) {
            System.out.println("树为空！");
            return;
        }
        root.preOrder();
    }
}

/**
 * 创建节点类Node
 * Node中包含data（存放数据）、weight （权）、left（左指针）、right（右指针）
 */
class Node implements Comparable<Node> {
    public Byte data; // 数据，存放数据(字符)本身，比如'a' => 97 ，' ' => 32
    public int weight;// 权值, 表示字符出现的次数
    public Node left;
    public Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
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
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}


