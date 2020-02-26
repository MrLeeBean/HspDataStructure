package com.shuai.queue;

import java.util.Scanner;

/**
 * 数组模拟队列测试
 */
public class ArrayQueueMain {
    public static void main(String[] args) {
//        ArrayQueue queue = new ArrayQueue(3);
        ArrayQueueCircle queue = new ArrayQueueCircle(3);
        Scanner scanner = new Scanner(System.in);//接收用户输入

        boolean loop = true;
        String key = "";

        while (loop) {
            System.out.println
                    (
                            "\n|-----请输入指令：输入add添加数据 输入get取出数据 输入head展示头部数据 输入show显示队列 输入exit退出程序-----|"
                    );
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
