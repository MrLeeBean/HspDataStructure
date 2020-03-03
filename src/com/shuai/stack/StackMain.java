package com.shuai.stack;

import java.util.Scanner;

public class StackMain {
    public static void main(String[] args) {

//        ArrayStack stack = new ArrayStack(3);
        LinkedListStack stack = new LinkedListStack(3);

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;//控制是否退出菜单
        String key = "";
        while (loop) {
            System.out.println("\n|---输入指令：push(入栈),pop(出栈),show(展示栈),exit(退出)---|");
            key = scanner.next();
            switch (key) {
                case "push":
                    System.out.println("请输入数据：");
                    String i = scanner.next();
                    if (isInt(i)) {
                        stack.push(Integer.parseInt(i));
                    } else {
                        System.out.println("输入的非int类型");
                    }
                    break;

                case "pop":
                    try {
                        int pop = stack.pop();
                        System.out.println("取出的数据为:" + pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;

                case "show":
                    stack.show();
                    break;

                case "exit":
                    scanner.close();
                    loop = false;
                    break;

                default:
                    System.out.println("输入非法");
                    break;
            }
        }
        System.out.println("程序退出...");

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
