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

        System.out.println("----修改后----");
        list.update(new HeroNode(2, "BBBBBBB", "bbbbbbb"));
        list.list();

        System.out.println("----修改后----");
        list.update(new HeroNode(2000, "QQQQQQ", "qqqqqq"));
        list.list();
    }
}
