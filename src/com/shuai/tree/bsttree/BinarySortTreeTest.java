package com.shuai.tree.bsttree;

/**
 * 二叉排序树
 */
public class BinarySortTreeTest {

    public static void main(String[] args) {

        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree tree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for (int i : arr) {
            tree.add(new Node(i));
        }
        tree.infixOrder();

        //删除节点
        tree.delNode(7);
        tree.delNode(3);
        tree.delNode(10);
        tree.delNode(12);
        tree.delNode(5);
        tree.delNode(1);
        tree.delNode(9);
        tree.delNode(2);

        System.out.println("\n删除结束\n");
        tree.infixOrder();

    }
}

/**
 * 二叉排序树类
 */
class BinarySortTree {
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

    /**
     * 根据value，查找对应的Node
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    /**
     * 根据value查找对应Node的父节点parentNode
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    /**
     * 删除节点
     *
     * @param value 待删除节点的值
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        }
        //找到待删除的节点
        Node targetNode = search(value);
        //找到待删除节点的父节点
        Node parentNode = searchParent(value);

        //待删除节点Node不为null的情况下
        if (targetNode != null) {
            //一、待删除节点是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                if (parentNode != null) {
                    if (parentNode.left != null && parentNode.left.value == targetNode.value) {//被删除的节点是父节点的左子节点
                        parentNode.left = null;
                    } else if (parentNode.right != null && parentNode.right.value == targetNode.value) {//被删除的节点是父节点的右子节点
                        parentNode.right = null;
                    } else {
                        //不会存在其他情况
                    }
                } else {
                    //父节点为空。说明targetNode为根节点
                    root = null;
                }
            }
            //二、待删除节点是只有一颗左子树的节点
            if (targetNode.left != null && targetNode.right == null) {
                if (parentNode != null) {
                    if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                        parentNode.left = targetNode.left;
                    } else if (parentNode.right != null && parentNode.right.value == targetNode.value) {
                        parentNode.right = targetNode.left;
                    } else {
                        //不会存在其他情况
                    }
                } else {
                    //父节点为空。说明targetNode为根节点
                    root = targetNode.left;
                }
            }
            //三、待删除节点是只有一颗右子树的节点
            if (targetNode.right != null && targetNode.left == null) {
                if (parentNode != null) {
                    if (parentNode.left != null && parentNode.left.value == targetNode.value) {
                        parentNode.left = targetNode.right;
                    } else if (parentNode.right != null && parentNode.right.value == targetNode.value) {
                        parentNode.right = targetNode.right;
                    } else {
                        //不会存在其他情况
                    }
                } else {
                    //父节点为空。说明targetNode为根节点
                    root = targetNode.right;
                }
            }
            //四、待删除节点是有两颗子树的节点
            if (targetNode.left != null && targetNode.right != null) {
                targetNode.value = deleteTreeMin(targetNode.right);
            }
        }

    }


    /**
     * 删除 node 为根结点的二叉排序树的最小结点
     * 并返回以node为根结点的二叉排序树的最小结点的值
     *
     * @param node
     * @return
     */
    public int deleteTreeMin(Node node) {
        if (node == null) {
            throw new RuntimeException();
        }
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
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
        return "Node{" +
                "value=" + value +
                '}';
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
    }

    /**
     * 根据value，查找对应的Node（递归查找）
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        }
        if (this.left != null && value < this.value) {
            return this.left.search(value);
        }
        if (this.right != null && value >= this.value) {
            return this.right.search(value);
        }
        return null;
    }

    /**
     * 根据value查找对应Node的父节点parentNode（递归查找）
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value)
                || this.right != null && this.right.value == value) {
            return this;
        } else {
            if (this.left != null && value < this.value) {
                return this.left.searchParent(value);
            }
            if (this.right != null && value >= this.value) {
                return this.right.searchParent(value);
            }
            return null;
        }
    }

}
