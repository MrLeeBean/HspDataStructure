package com.shuai.linkedlist;

public class SingleLinkedListMain {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "A", "a");
        HeroNode hero2 = new HeroNode(2, "B", "b");
        HeroNode hero3 = new HeroNode(3, "C", "c");
        HeroNode hero4 = new HeroNode(4, "D", "d");

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
