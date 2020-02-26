package com.shuai.queue;

/**
 * 数组模拟队列
 */
public class ArrayQueue {

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