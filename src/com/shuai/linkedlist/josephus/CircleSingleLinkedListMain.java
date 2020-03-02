package com.shuai.linkedlist.josephus;

public class CircleSingleLinkedListMain {
    public static void main(String[] args) {
        CircleSingleLinkedList linkedList = new CircleSingleLinkedList();
        linkedList.add(5);
        linkedList.showBoy();

        System.out.println("-----约瑟夫问题-----");
        linkedList.countBoy(1, 2, 5);
    }
}
