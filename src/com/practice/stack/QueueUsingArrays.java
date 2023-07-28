package com.practice.stack;

// using circular array - i % n everywhere
public class QueueUsingArrays {
    int front;
    int rear;
    // size var is IMPORTANT
    int size;
    int capacity;
    int arr[];

    QueueUsingArrays(int cap) {
        front = 0;
        rear = 0;
        size = 0;
        capacity = cap;
        arr = new int[capacity];
    }

    void enqueue(int x) {
        if (size == capacity) {
            throw new Error("Queue overflow");
        }

        arr[rear % capacity] = x;
        rear++;
        size++;
    }

    int dequeue() {
        if (size == 0) {
            throw new Error("Queue underflow");
        }

        int temp = arr[front % capacity];
        front++;
        size--;
        return temp;
    }

    int peek() {
        if (size == 0) {
            throw new Error("Queue underflow");
        }

        return arr[front % capacity];
    }

    int size() {
        return size;
    }

    boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        QueueUsingArrays q = new QueueUsingArrays(3);
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println(q.peek());
        System.out.println(q.dequeue());
        System.out.println(q.peek());
        q.dequeue();
        System.out.println(q.dequeue());
        // q.dequeue();

        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);

        System.out.println(q.peek());
        System.out.println(q.dequeue());
        System.out.println(q.peek());
        q.dequeue();
        System.out.println(q.dequeue());
        q.dequeue();
    }

}
