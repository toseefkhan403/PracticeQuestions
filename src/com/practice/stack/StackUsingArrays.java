package com.practice.stack;

public class StackUsingArrays {
    int capacity = 0;
    int top = -1;
    int[] arr;

    public StackUsingArrays(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        top = -1;
    }

    public void push(int element) {
        // overflow condition
        if (top == capacity - 1) {
            throw new Error("Stack Overflow");
        }

        arr[++top] = element;
    }

    public int pop() {
        if (top == -1) {
            throw new Error("Stack Underflow");
        }

        int el = arr[top];
        top--;
        return el;
    }

    public int peek() {
        if (top == -1) {
            throw new Error("Stack Underflow");
        }

        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public static void main(String[] args) {
        StackUsingArrays stack = new StackUsingArrays(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.pop());
        stack.pop();
    }
}
