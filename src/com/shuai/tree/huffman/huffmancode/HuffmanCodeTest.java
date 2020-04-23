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

        //获取赫夫曼编码表
        System.out.println("\n赫夫曼编码表结果：");
        Map<Byte, String> codes = getCodes(rootNode);
        for (Map.Entry<Byte, String> entry : codes.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
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

    // 将赫夫曼编码表存放在 Map<Byte,String> 中
    // 生成的赫夫曼编码表为： {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    public static Map<Byte, String> huffmanCodes = new HashMap<>();

    /**
     * 生成赫夫曼树对应的赫夫曼编码表
     *
     * @param root 根节点
     * @return 赫夫曼编码表
     */
    public static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        // 生成赫夫曼编码表，需要去拼接路径, 所以定义一个StringBuilder用于存储某个叶子节点的路径
        StringBuilder builder = new StringBuilder();
        // 处理root的左子树
        getCodes(root.left, "0", builder);
        // 处理root的右子树
        getCodes(root.right, "1", builder);

        return huffmanCodes;
    }

    /**
     * 功能：将传入的node节点的所有叶子节点的赫夫曼编码（码字）得到，并放入到huffmanCodes集合
     *
     * @param node    节点
     * @param code    节点的路径：此节点是其父节点的左子节点 路径code为 0, 此节点是其父节点的右子节点 路径code为1
     * @param builder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder builder) {
        StringBuilder builder1 = new StringBuilder(builder);
        //将 code 加入到 builder1
        builder1.append(code);
        if (node != null) {//如果node == null不处理
            //判断 当前node 是叶子节点还是非叶子节点
            if (node.data == null) {//非叶子节点
                //递归处理:向左递归
                getCodes(node.left, "0", builder1);
                //递归处理:向右递归
                getCodes(node.right, "1", builder1);
            } else {//叶子节点
                huffmanCodes.put(node.data, builder1.toString());
            }
        }
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


