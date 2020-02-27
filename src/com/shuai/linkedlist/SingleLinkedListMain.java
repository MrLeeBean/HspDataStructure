package com.shuai.linkedlist;

public class SingleLinkedListMain {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(hero2);
        list.addByOrder(hero1);
        list.addByOrder(hero3);
        list.addByOrder(hero4);
        list.list();

//        System.out.println("---------获取链表长度---------");
//        int length = SingleLinkedListUtil.getLength(list.getHead());
//        System.out.println("链表长度为" + length);

//        System.out.println("---------查找单链表中的倒数第k个结点---------");
//        int lastIndex = 100;
//        HeroNode lastIndexNode = SingleLinkedListUtil.findLastIndexNode(list.getHead(), lastIndex);
//        System.out.println("倒数第" + lastIndex + "个节点为:" + lastIndexNode);

        SingleLinkedListUtil.reverseList(list.getHead());
        System.out.println("---------反转后---------");
        list.list();
    }
}
