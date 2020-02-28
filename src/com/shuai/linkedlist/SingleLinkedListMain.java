package com.shuai.linkedlist;

public class SingleLinkedListMain {

    public static void main(String[] args) {

        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        SingleLinkedListDouble list = new SingleLinkedListDouble();
        list.addByOrder(hero2);
        list.addByOrder(hero1);
        list.addByOrder(hero3);
        list.addByOrder(hero4);
        list.list();

        System.out.println("------删除后------");
        list.del(4);
        list.del(100);
        list.list();
    }
}
