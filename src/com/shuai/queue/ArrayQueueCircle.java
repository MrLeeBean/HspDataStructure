package com.shuai.queue;

/**
 * 数组模拟环形队列
 */
public class ArrayQueueCircle {

    private int rear;
    private int front;
    private int maxSize;
    private int[] queue;

    public ArrayQueueCircle(int maxSize) {
        if (maxSize <= 0) throw new RuntimeException("队列长度必须大于0");
        this.maxSize = maxSize;
        this.rear = 0;
        this.front = 0;
        queue = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int item) {
        if (isFull()) {
            System.out.println("添加数据失败，队列已满");
            return;
        }
        queue[rear] = item;
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("取出数据失败，队列为空");
        }
        int i = queue[front];
        front = (front + 1) % maxSize;
        return i;
    }

    public void showAllQueue() {
        for (int i = front; i < front + size(); i++) {
            System.out.printf("queue[%d] = %d\n", i % maxSize, queue[i % maxSize]);
        }
    }

    public int showHeadQueue() {
        if (isEmpty()) {
            throw new RuntimeException("展示头数据失败，队列为空");
        }
        return queue[front];
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }
}