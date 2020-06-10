package com.shuai.tree.bsttree;

/**
 * 二叉排序树
 */
public class BinarySortTreeTest {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree tree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i : arr) {
            tree.add(new Node(i));
        }
        tree.infixOrder();
    }
}

/**
 * 二叉排序树类
 */
class BinarySortTree {
    private Node root;

    /**
     * 添加节点
     *
     * @param node
     */
    public void add(Node node) {
        if (root == null) {
            //如果root为空则直接让root指向node
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历二叉树
     */
    public void infixOrder() {
        if (root == null) {
            return;
        }
        root.infixOrder();
    }

}

/**
 * 节点类
 */
class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    /**
     * 添加节点
     * 采用递归的形式添加结点，需要满足二叉排序树的要求
     *
     * @param node
     */
    public void add(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {//添加的结点的值小于当前结点的值
            if (this.left != null) {
                //递归向左子树添加
                this.left.add(node);
            } else {
                this.left = node;
            }
        } else {//添加的结点的值大于等于当前结点的值
            if (this.right != null) {
                //递归向右子树添加
                this.right.add(node);
            } else {
                this.right = node;
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.infixOrder();
        }
    }
}
