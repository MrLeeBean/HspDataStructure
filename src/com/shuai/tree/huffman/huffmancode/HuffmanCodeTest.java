package com.shuai.tree.huffman.huffmancode;

import java.util.*;

/**
 * 赫夫曼编码
 */
public class HuffmanCodeTest {
    public static void main(String[] args) {

        String content = "i like like like java do you like a java";
        /*
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
        */
        //将原始字符串，经过赫夫曼编码，压缩存入byte[]数组
        byte[] huffmanCodesBytes = huffmanZip(content.getBytes());
        System.out.println("\n压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + " 长度= " + huffmanCodesBytes.length);
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

    /**
     * 将原始字符串对应的byte[] 数组，转换为赫夫曼编码，并压缩存入byte[]数组（压缩）
     *
     * @param bytes 原始的字符串对应的字节数组
     * @return      赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    public static byte[] huffmanZip(byte[] bytes) {

        //将字节数组转换为List<Node>
        List<Node> nodes = getNodes(bytes);
        //通过List<Node> 创建对应的赫夫曼树
        Node huffmanTree = createHuffmanTree(nodes);
        //生成赫夫曼树对应的赫夫曼编码表
        Map<Byte, String> codes = getCodes(huffmanTree);

        //将原始字节数组bytes，通过查找编码表codes，生成最终的赫夫曼编码对应的字符串
        //字符串："1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(codes.get(b));
        }

        // 因为是每8位对应一个byte
        // 所以这个字符串 需要按照每8个分割一组，将每组的字符串转成对应的二进制数值，存储到字节里(byte)，最终全部存储到字节数组(byte[])中。
        int len;//计算需要存储的字节数组长度
        if (builder.length() % 8 == 0) {
            len = builder.length() / 8;
        } else {
            len = builder.length() / 8 + 1;
        }

        //创建字节数组
        byte[] resultBytes = new byte[len];

        // 10101000   10111111   1100100    01011...
        // 除8 = 0    1          2          3    ...
        // [0 --7)    [8-->15)  [16-->23)  [24...
        for (int i = 0; i < builder.length(); i += 8) {//i = 0、8、16、24...
            if (i + 8 > builder.length() - 1) {//执行到最后一次循环了
                resultBytes[i / 8] = (byte) Integer.parseInt(builder.substring(i), 2);
            } else {//还有下次循环。
                resultBytes[i / 8] = (byte) Integer.parseInt(builder.substring(i, i + 8), 2);
            }
        }
        return resultBytes;
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


