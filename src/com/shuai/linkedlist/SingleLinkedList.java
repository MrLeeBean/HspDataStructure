package com.shuai.linkedlist;

public class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点数据到链表
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;//辅助节点
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 按照顺序添加节点数据到链表
     * <p>
     * 根据排名将英雄插入到指定位置
     * 如果有这个排名，则添加失败，并给出提示
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为这是单链表，所以我们找的temp是位于添加位置的前一个节点，否则插入不了

        HeroNode temp = head;
        boolean flag = false; // flag标志添加的编号是否存在，默认为false

        //开始寻找位置：

        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后。结束循环。
                break;
            }
            if (temp.next.no > heroNode.no) { //位置找到，就在temp的后面插入。结束循环。
                break;
            } else if (temp.next.no == heroNode.no) {//说明希望添加的heroNode的编号已然存在。结束循环。
                flag = true; //说明编号存在
                break;
            }
            temp = temp.next; //后移，继续遍历当前链表寻找位置
        }
        //判断flag 的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


    /**
     * 单链表节点的修改
     * <p>
     * 修改节点的信息, 根据no编号来修改，但是no编号不能改.
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表不能为空");
            return;
        }
        HeroNode temp = head.next;  //定义辅助节点
        boolean hasFind = false;    //是否找到要修改的节点

        //开始查找位置.

        while (true) {
            if (temp == null) {//说明temp已经在链表的最后。结束循环。
                break;
            }
            if (temp.no == newHeroNode.no) {//节点找到。结束循环。
                hasFind = true;
                break;
            }
            temp = temp.next;//后移，继续遍历当前链表寻找位置
        }

        //根据hasFind判断是否找到要修改的节点

        if (hasFind) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("准备修改的英雄编号%d不存在\n", newHeroNode.no);
        }

    }

    /**
     * 遍历链表中的节点数据
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表不能为空");
            return;
        }
        //创建辅助节点，移动向头结点的下一节点
        HeroNode temp = head.next;
        while (temp != null) {//节点不为空
            //1.输出节点信息
            System.out.println(temp);
            //2.辅助节点继续后移，指向下一个节点
            temp = temp.next;
        }
    }
}
