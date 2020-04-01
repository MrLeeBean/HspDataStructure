package com.shuai.tree.ergodic;

/**
 * 前序中序后续遍历
 */
public class BinaryTreeTest {
    public static void main(String[] args) {

        //创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //创建二叉树
        BinaryTree tree = new BinaryTree();
        tree.setRoot(root);
        root.left = node2;
        root.right = node3;
        node3.left = node5;
        node3.right = node4;

        System.out.println("前序遍历-->");
        tree.preOrder();

        System.out.println("中序遍历-->");
        tree.infixOrder();

        System.out.println("后序遍历-->");
        tree.postOrder();

    }
}

/**
 * 定义 BinaryTree 二叉树
 */
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.preOrder();
    }

    //中序遍历
    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.infixOrder();
    }

    //后序遍历
    public void postOrder() {
        if (root == null) {
            System.out.println("二叉树为空");
            return;
        }
        root.postOrder();
    }
}

/**
 * 定义 HeroNode 节点
 */
class HeroNode {
    public int no;
    public String name;
    public HeroNode left; // 默认null
    public HeroNode right;// 默认null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ " + "no=" + no + ",name=" + name +
                (left == null ? ",left=null" : ",left.no=" + left.no) +
                (right == null ? ",right=null" : ",right.no=" + right.no) +
                " ]";
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (left != null) {
            left.infixOrder();
        }
        System.out.println(this);
        if (right != null) {
            right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (left != null) {
            left.postOrder();
        }
        if (right != null) {
            right.postOrder();
        }
        System.out.println(this);
    }

}
