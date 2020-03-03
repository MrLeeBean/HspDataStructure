package com.shuai.stack;

import java.util.Stack;

/**
 * 单链表模拟战
 */
public class LinkedListStack {
    private Node head = new Node(-1000);
    private int maxSize;

    public LinkedListStack(int maxSize) {
        if (maxSize <= 0) {
            throw new RuntimeException("maxSize不合法");
        }
        this.maxSize = maxSize;
    }

    //获取链表长度
    private int getLength() {
        if (head.next == null) {
            return 0;
        }
        Node cur = head.next;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        return len;
    }

    //添加节点到链表尾部
    private void addToEnd(Node node) {
        Node cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = node;
    }


    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return getLength() == maxSize;
    }

    /**
     * 栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        addToEnd(new Node(value));
    }

    /**
     * 出栈
     * 将栈顶的数据返回
     *
     * @return
     */
    public int pop() {

        //删除单链表的最后一个节点,需要寻找最后节点的前一个节点。

        //head -- > null
        if (head.next == null) {
            throw new RuntimeException("栈空");
        }

        //head -- > item1 --> null
        if (head.next.next == null) {
            Node result = head.next;
            head.next = null;//置空链表
            return result.data;
        }

        //head -- > item1 --> item2 --->null
        //head -- > item1 --> item2 --->item3 --> null
        //...
        Node cur = head;
        while (true) {
            if (cur.next != null && cur.next.next == null) {
                break;
            }
            cur = cur.next;
        }
        //cur xx null
        Node result = cur.next;
        cur.next = null;//最后一个item置空
        return result.data;
    }

    /**
     * 显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
     */
    public void show() {
        if (head.next == null) {
            System.out.println("栈空");
            return;
        }
        Node cur = head.next;
        Stack<Node> stack = new Stack<>();
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        //需要从栈顶开始显示数据
        System.out.println("<---栈顶--->");
        while (!stack.empty()) {
            System.out.println(stack.pop().data);
        }
        System.out.println("<---栈底--->");
    }
}

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "data = " + data + ",next.data = " + (next != null ? next.data : null);
    }
}
