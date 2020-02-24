package com.shuai.queue;

import java.util.Scanner;

/**
 * 数组模拟队列
 */
public class ArrayQueueTest {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);//接收用户输入

        boolean loop = true;
        String key = "";

        while (loop) {
            System.out.println("\n|-----请输入指令：输入add添加数据 输入get取出数据 输入head展示头部数据 输入show显示队列 输入exit退出程序-----|");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入一个数：");
                    String value = scanner.next();
                    if (isInt(value)) {
                        queue.addQueue(Integer.parseInt(value));
                    } else {
                        System.out.println("输入的非int类型");
                    }
                    break;
                case "get":
                    try {
                        int i = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", i);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "head":
                    try {
                        int head = queue.showHeadQueue();
                        System.out.printf("展示的头部数据是%d\n", head);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "show":

                    queue.showAllQueue();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    System.out.println("程序已退出");
                    break;
                default:
                    System.out.println("输入非法！");
                    break;
            }

        }


    }

    public static boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

class ArrayQueue {

    //    rear 是队列最后[含]
    //    front 是队列最前元素[不含]

    private int rear;       // 队列尾
    private int front;      // 队列头
    private int maxSize;    // 表示数组的最大容量
    private int[] queue;    // 该数据用于存放数据, 模拟队列

    public ArrayQueue(int maxSize) {
        if (maxSize <= 0) throw new RuntimeException("队列长度必须大于0");
        this.maxSize = maxSize;
        this.rear = -1;
        this.front = -1;
        queue = new int[maxSize];
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public void addQueue(int item) {
        if (isFull()) {
            System.out.println("添加数据失败，队列已满");
            return;
        }
        queue[++rear] = item;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("取出数据失败，队列为空");
        }
        return queue[++front];
    }

    public int showHeadQueue() {
        if (isEmpty()) {
            throw new RuntimeException("展示头数据失败，队列为空");
        }
        return queue[front + 1];
    }

    public void showAllQueue() {
        for (int i = 0; i < queue.length; i++) {
            System.out.printf("queue[%d] = %d\n", i, queue[i]);
        }
    }

}
