package com.shuai.tree;

/**
 * 顺序存储二叉树
 */
public class ArrayBinaryTreeTest {
    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("\n------前序遍历------");
        arrBinaryTree.preOrder(0);
        System.out.println("\n------中序遍历------");
        arrBinaryTree.infixOrder(0);
        System.out.println("\n------后序遍历------");
        arrBinaryTree.postOrder(0);
    }
}

class ArrayBinaryTree {
    private int[] arr;//存储数据结点的数组

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历还原二叉树
     *
     * @param index
     */
    public void preOrder(int index) {
        if (arr == null && arr.length <= 0) {
            System.out.println("数组为空，不能还原二叉树");
            return;
        }
        if (index <= arr.length - 1) {
            // 输出当前这个元素
            System.out.print(arr[index] + " ");
            // 向左递归遍历
            if (2 * index + 1 <= arr.length - 1) {
                preOrder(2 * index + 1);
            }
            // 向右递归遍历
            if (2 * index + 2 <= arr.length - 1) {
                preOrder(2 * index + 2);
            }
        }

    }

    /**
     * 中序遍历还原二叉树
     *
     * @param index
     */
    public void infixOrder(int index) {
        if (arr == null && arr.length <= 0) {
            System.out.println("数组为空，不能还原二叉树");
            return;
        }
        if (index <= arr.length - 1) {
            if (2 * index + 1 <= arr.length - 1) {
                infixOrder(2 * index + 1);
            }
            System.out.print(arr[index] + " ");
            if (2 * index + 2 <= arr.length - 1) {
                infixOrder(2 * index + 2);
            }
        }
    }

    /**
     * 后续遍历还原二叉树
     *
     * @param index
     */
    public void postOrder(int index) {
        if (arr == null && arr.length <= 0) {
            System.out.println("数组为空，不能还原二叉树");
            return;
        }
        if (index <= arr.length - 1) {
            if (2 * index + 1 <= arr.length - 1) {
                postOrder(2 * index + 1);
            }
            if (2 * index + 2 <= arr.length - 1) {
                postOrder(2 * index + 2);
            }
            System.out.print(arr[index] + " ");
        }
    }
}
