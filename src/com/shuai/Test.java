package com.shuai;

public class Test {

    public static void main(String[] args) {

        LinkedNode link = new LinkedNode();
        link.addNode(new Node(1, "A"));
        link.addNode(new Node(2, "b"));
        link.addNode(new Node(3, "c"));
        link.listNode();
    }
}

class LinkedNode {

    private Node head = new Node(0, "");

    public void addNode(Node node) {
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;

    }

    public void listNode() {
        if (head.next == null) {
            return;
        }
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }
}

class Node {
    public int id;
    public String name;
    public Node next;

    public Node(int id, String name) {
        this.id = id;
        this.name = name;
    }


    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", next=" + next +
                '}';
    }
}
