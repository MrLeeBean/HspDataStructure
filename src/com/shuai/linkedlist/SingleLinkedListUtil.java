package com.shuai.linkedlist;

import java.util.Stack;

public class SingleLinkedListUtil {

    /**
     * 根据头节点获取单链表长度
     *
     * @param head 头节点
     * @return 返回有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表为空");
            return 0;
        }
        HeroNode curNode = head.next;
        int length = 0;
        while (curNode != null) {
            length++;
            curNode = curNode.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个结点
     *
     * @param head  头结点
     * @param index 倒数第index个节点
     * @return 返回节点
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;
        }
        //链表总长度
        int size = getLength(head);
        //如果index校验失败，返回null
        if (index <= 0 || index > size) {
            return null;
        }
        //正着数，要遍历的个数
        int len = size - index;
        HeroNode curNode = head.next;
        for (int i = 0; i < len; i++) {
            curNode = curNode.next;
        }
        return curNode;
    }

    /**
     * 单链表的反转
     *
     * @param head
     */
    public static void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) {
            return;
        }
        //定义一个新的头节点
        HeroNode reverseHead = new HeroNode(0, "", "");
        //定义一个辅助节点（当前节点）,帮助遍历原来的链表
        HeroNode curNode = head.next;
        while (curNode != null) {
            HeroNode nextNode = curNode.next;//用一个临时的nextNode存储当前节点的下一个节点，因为后面需要使用
            curNode.next = reverseHead.next; //将curNode的next节点 指向 新的链表的最前端（即链表新头节点reverseHead.next指向的节点）
            reverseHead.next = curNode;      //将curNode连接到新的链表上（即链表新头节点reverseHead.next指向curNode）
            curNode = nextNode;              //将curNode继续后移
        }

        head.next = reverseHead.next;
    }

    /**
     * 逆序打印单链表
     * 可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     *
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;//空链表，不能打印
        }
        Stack<HeroNode> stack = new Stack<>();//创建要给一个栈，将各个节点压入栈
        HeroNode curNode = head.next;
        //将链表的所有节点压入栈
        while (curNode != null) {
            stack.push(curNode);
            curNode = curNode.next;
        }
        //将栈中的节点进行打印,pop 出栈
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
