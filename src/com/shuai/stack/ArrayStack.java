package com.shuai.stack;

/**
 * 数组模拟栈
 */
public class ArrayStack {
    private int maxSize;    //栈的大小
    private int top = -1;   //top表示栈顶，初始化为-1
    private int[] stack;    //数组模拟栈，数据就放在该数组

    public ArrayStack(int maxSize) {
        if (maxSize <= 0) {
            throw new RuntimeException("maxSize不合法");
        }
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * 将栈顶的数据返回
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 显示栈顶数据
     * @return
     */
    public int peek(){
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        return stack[top];
    }

    /**
     * 显示栈的情况[遍历栈]， 遍历时，需要从栈顶开始显示数据
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        //需要从栈顶开始显示数据
        System.out.println("<---栈顶--->");
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
        System.out.println("<---栈底--->");
    }
}
