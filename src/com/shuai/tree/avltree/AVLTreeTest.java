package com.shuai.tree.avltree;

/**
 * 二叉平衡树
 */
public class AVLTreeTest {

    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree tree = new AVLTree();
        for (int i : arr) {
            tree.add(new Node(i));
        }
        tree.infixOrder();
    }

}

/**
 * 二叉平衡树类
 */
class AVLTree {

    private Node root;

    /**
     * 中序遍历二叉树
     */
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.infixOrder();
    }

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
}

/**
 * 节点类
 */
class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" +
                "value=" + value +
                ", left.value=" + (left == null ? "null" : left.value) +
                ", right.value=" + (right == null ? "null" : right.value) +
                "]";
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

        //////////////////////////////////////////////////////
        //
        //              二叉排序树转二叉平衡树
        //
        /////////////////////////////////////////////////////

        //当添加完一个结点后，如果: (右子树的高度-左子树的高度) > 1 , 左旋转
        if (rightHeight() - leftHeight() > 1) {//左旋转
            //如果它的 右子树的左子树的高度 大于 它的右子树的右子树的高度
            if (this.right != null && this.right.leftHeight() > this.right.rightHeight()) {
                //先对右子结点进行右旋转
                this.right.rightRotate();
                //然后在对当前结点进行左旋转
                this.leftRotate();
            } else {
                //直接进行左旋转即可
                this.leftRotate();
            }
            return;//必须return，否则会执行后面代码

        }

        //当添加完一个结点后，如果 (左子树的高度 - 右子树的高度) > 1, 右旋转
        if (leftHeight() - rightHeight() > 1) {//右旋转
            //如果它的 左子树的右子树高度 大于 它的左子树的高度
            if (this.left != null && this.left.rightHeight() > this.left.leftHeight()) {
                //先对当前结点的左结点(左子树)->左旋转
                this.left.leftRotate();
                //再对当前结点进行右旋转
                this.rightRotate();
            } else {
                //直接进行右旋转即可
                this.rightRotate();
            }
            return;
        }
    }

    /**
     * 返回 以该结点为根结点的树的高度
     *
     * @return
     */
    public int height() {
        return Math.max
                (
                        this.left == null ? 0 : this.left.height(),
                        this.right == null ? 0 : this.right.height()
                ) + 1;
    }

    /**
     * 返回 以该结点为根结点的左子树的高度
     *
     * @return
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return this.left.height();
    }

    /**
     * 返回 以该结点为根结点的右子树的高度
     *
     * @return
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return this.right.height();
    }

    /**
     * 左旋转
     */
    public void leftRotate() {
        Node newNode = new Node(this.value);
        newNode.left = this.left;
        newNode.right = this.right.left;
        this.value = this.right.value;
        this.right = this.right.right;
        this.left = newNode;
    }

    /**
     * 右旋转
     */
    public void rightRotate() {
        Node newNode = new Node(this.value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = this.left.value;
        this.left = this.left.left;
        this.right = newNode;
    }

}

