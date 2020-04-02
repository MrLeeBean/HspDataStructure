package com.shuai.tree.del;

/**
 * 删除节点
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
        root.left = node3;
        root.right = node2;
        node3.left = node5;
        node3.right = node4;

        //删除节点前
        tree.preOrder();
        System.out.println("\n" + "删除结果：" + tree.delNode(5) + "\n");
        //删除节点后
        tree.preOrder();

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

    /**
     * 删除节点
     *
     * @param no 节点id
     * @return 是否删除成功
     */
    public boolean delNode(int no) {
        if (root == null) {
            System.out.println("二叉树为空");
            return false;
        }
        if (root.no == no) {
            root = null;
            return true;
        }
        return root.delNode(no);
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

    /**
     * 删除节点
     *
     * @param no 节点id
     * @return 是否删除成功
     */
    public boolean delNode(int no) {
        // 如果当前结点的左子结点不为空，并且左子结点 就是要删除结点，就将this.left = null; 并且就返回(结束递归删除)
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return true;
        }
        // 如果当前结点的右子结点不为空，并且右子结点 就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return true;
        }
        boolean flag = false;
        // 左子节点不为空，则向左子树进行递归删除
        if (this.left != null) {
            flag = this.left.delNode(no);
        }
        if (flag) {//左子节点删除成功，则结束递归
            return true;
        }
        // 右子节点不为空，则向右子树进行递归删除
        if (this.right != null) {
            return this.right.delNode(no);
        }
        return false;
    }
}