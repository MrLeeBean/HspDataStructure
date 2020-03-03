package com.shuai.linkedlist.josephus;

/**
 * 约瑟夫问题：单向环形链表
 */
public class CircleSingleLinkedList {

    private Boy first = null;// 创建一个first节点,当前没有编号

    /**
     * 添加Boy节点，构建成一个环形的链表
     *
     * @param nums
     */
    public void add(int nums) {
        if (nums < 1) {
            System.out.printf("nums的值不正确，无法添加%d个元素\n", nums);
        }
        Boy curBoy = null;//辅助变量（指针）
        //创建环形链表
        for (int i = 1; i <= nums; i++) {
            // 根据编号，创建Boy节点
            Boy boy = new Boy(i);
            // 如果是第一个Boy节点
            if (i == 1) {
                first = boy;
                first.setNext(boy);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    /**
     * 遍历当前环形链表
     */
    public void showBoy() {
        //链表判空
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        // 因为first不能动，因此我们仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true) {
            System.out.println("编号：" + curBoy.getNo());
            if (curBoy.getNext() == first) {//遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     *
     * @param startNo  表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums     表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {
        //校验数据合法性
        if (startNo <= 0 || countNum <= 0 || nums <= 0 || startNo > nums) {
            System.out.println("数据不合法");
        }

        //创建辅助指针(变量) helper
        Boy helper = first;
        //辅助指针事先需要指向环形链表最后一个节点，即first的前一位
        while (true) {
            if (helper.getNext() == first) {// 说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();
        }

        //将辅助变量和first移动到startNo位
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }

        //执行循环操作，直到圈中只有一个节点
        while (true) {
            if (first == helper) {
                break;//只剩一位
            }
            //报数过程：让 first 和 helper 指针同时移动 countNum - 1位
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.println("出圈节点位置：" + first.getNo());

            //这时将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);

        }

        System.out.println("最后剩下的一位是：" + first.getNo());
    }
}
