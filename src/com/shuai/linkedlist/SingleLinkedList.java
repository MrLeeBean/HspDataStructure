package com.shuai.linkedlist;

/**
 * 单向链表
 */
public class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    /**
     * 添加节点数据到链表
     *
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;//辅助变量（指针）
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
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;  //定义辅助变量（指针）
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
     * 删除链表中的指定节点
     *
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean hasFind = false;// 标志是否找到待删除节点
        while (true) {
            if (temp.next == null) {//说明temp已经在链表的最后。结束循环。未找到。
                break;
            }
            if (temp.next.no == no) {//找到的待删除节点的前一个节点temp。结束循环。已找到。
                hasFind = true;
                break;
            }
            temp = temp.next;//后移，继续遍历当前链表寻找位置
        }
        if (hasFind) {//找到，可以删除
            temp.next = temp.next.next;
        } else {//未找到。提示。
            System.out.printf("没有找到编号为%d的节点英雄\n", no);
        }
    }

    /**
     * 遍历链表中的节点数据
     */
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //创建辅助变量（指针），移动向头结点的下一节点
        HeroNode temp = head.next;
        while (temp != null) {//节点不为空
            //1.输出节点信息
            System.out.println(temp);
            //2.辅助变量（指针）继续后移，指向下一个节点
            temp = temp.next;
        }
    }
}
