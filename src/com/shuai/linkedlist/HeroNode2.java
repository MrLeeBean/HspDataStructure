package com.shuai.linkedlist;

/**
 * 双向链表节点bean
 */
public class HeroNode2 {
    public int no;//英雄编号
    public String name;//姓名
    public String nickName;//昵称
    public HeroNode2 next;//下一个节点 默认null
    public HeroNode2 pre; //上一个节点 默认null

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "no = " + no +
                ",name=" + name +
                ",nickName=" + nickName +
                ",next.no=" + (next != null ? next.no : "null") +
                ",pre.no=" + (pre != null ? pre.no : "null");
    }
}
