package com.shuai.linkedlist;

/**
 * 单向链表节点bean
 */
public class HeroNode {
    public int no;//英雄编号
    public String name;//姓名
    public String nickName;//昵称
    public HeroNode next;//下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                ", next=" + next +
                '}';
    }
}
