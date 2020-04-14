package com.shuai.tree.threaded;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeTest {

    public static void main(String[] args) {
        HeroNode node_a = new HeroNode(1, "A");
        HeroNode node_b = new HeroNode(2, "B");
        HeroNode node_c = new HeroNode(3, "C");
        HeroNode node_d = new HeroNode(4, "D");
        HeroNode node_e = new HeroNode(5, "E");
        HeroNode node_f = new HeroNode(6, "F");
        HeroNode node_g = new HeroNode(7, "G");
        HeroNode node_h = new HeroNode(8, "H");
        HeroNode node_i = new HeroNode(9, "I");

        node_a.left = node_b;
        node_a.right = node_c;

        node_b.left = node_d;
        node_b.right = node_e;

        node_d.left = node_h;
        node_d.right = node_i;

        node_c.left = node_f;
        node_c.right = node_g;

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(node_a);
        //中序线索化二叉树
        tree.infixThreadedTree(node_a);
        //遍历中序线索化二叉树
        tree.infixThreadedTreeList();
    }

}


class ThreadedBinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    //为了实现线索化，需要创建 当前节点的前驱节点，在递归进行线索化时，pre总是保留前一个节点
    private HeroNode pre;

    /**
     * 中序线索化二叉树
     *
     * @param node
     */
    public void infixThreadedTree(HeroNode node) {
        //如果node==null, 不能线索化
        if (node == null) {
            return;
        }

        /**(一)先线索化左子树*/
        infixThreadedTree(node.left);

        /**(二)线索化当前节点*/
        //处理当前节点的前驱节点
        if (node.left == null) {
            //让当前节点的左指针指向前驱节点 
            node.left = pre;
            //修改当前节点的左指针的类型
            node.leftType = 1;
        }
        //处理后继节点（注意这里处理的是上一个节点，即前驱节点 的后继节点）
        if (pre != null && pre.right == null) {
            //让前驱节点的右指针指向当前节点
            pre.right = node;
            //修改前驱节点的右指针类型
            pre.rightType = 1;
        }
        //每处理一个结点后，让当前结点是下一个结点的前驱结点
        pre = node;

        /**(三)再线索化右子树*/
        infixThreadedTree(node.right);

    }

    /**
     * 遍历中序线索化二叉树
     */
    public void infixThreadedTreeList() {
        //定义一个变量，存储当前遍历的节点，从root开始
        HeroNode node = root;

        while (node != null) {
            //循环的找到leftType == 1的第一个节点（即第一个线索节点），此节点为 当前树中序遍历的第一个节点
            while (node.leftType == 0) {
                node = node.left;
            }
            //输出当前树 第一个节点
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点,就一直输出
            while (node.rightType == 1) {
                //获取到当前节点的后继节点
                node = node.right;
                //输出
                System.out.println(node);
            }
            // 找到普通节点
            // 继续下一次循环遍历
            node = node.right;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public HeroNode left; //左指针，默认null
    public HeroNode right; //右指针，默认null
    //说明
    //1. 如果leftType == 0 表示左指针指向的是左子树节点（指针为子节点指针）, 如果 1 表示左指针指向的是前驱节点（指针为线索指针）
    //2. 如果rightType == 0 表示右指针指向的是右子树节点（指针为子节点指针）, 如果 1 表示右指针指的是后继节点（指针为线索指针）
    public int leftType;
    public int rightType;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[ " + "no=" + no + ",name=" + name +
                (left == null ? ",left=null  " : ",left.name=" + left.name) +
                (right == null ? ",right=null  " : ",right.name=" + right.name) +
                ",leftType=" + leftType + ",rightType=" + rightType +
                " ]";
    }
}