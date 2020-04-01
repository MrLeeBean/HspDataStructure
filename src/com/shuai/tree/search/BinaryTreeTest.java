package com.shuai.tree.search;

/**
 * 前序中序后续查找
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

        int no = 5;

        System.out.println("===> 前序查找结果：" + tree.preOrderSearch(no) + "\n");

        System.out.println("===> 中序查找结果：" + tree.infixOrderSearch(no) + "\n");

        System.out.println("===> 后续查找结果：" + tree.postOrderSearch(no) + "\n");

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

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.preOrderSearch(no);
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.infixOrderSearch(no);
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return root.postOrderSearch(no);
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

    //前序查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序查找--作比较");
        if (this.no == no) {
            return this;
        }

        HeroNode resNode = null;
        if (left != null) {
            resNode = left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (right != null) {
            resNode = right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (left != null) {
            resNode = left.infixOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入中序查找--作比较");
        if (this.no == no) {
            return this;
        }

        if (right != null) {
            resNode = right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (left != null) {
            resNode = left.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        if (right != null) {
            resNode = right.postOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }

        System.out.println("进入后序查找--作比较");
        if (this.no == no) {
            return this;
        }
        return resNode;
    }

}