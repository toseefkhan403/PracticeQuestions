package com.practice.stack;

import com.practice.linked_list.Node;

// fill elements from the back of the LL - make the top element head
public class StackUsingLL {
    Node head;
    int size;

    public StackUsingLL() {
        head = null;
        size = 0;
    }

    public void push(int data) {
        Node newNode = new Node(data);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public int pop() {
        if (head == null) {
            throw new Error("Stack Underflow");
        }

        Node temp = head;
        head = head.getNext();
        temp.setNext(null);
        size--;
        return temp.getData();
    }

    public int peek() {
        if (head == null) {
            throw new Error("Stack Underflow");
        }

        return head.getData();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        StackUsingLL stack = new StackUsingLL();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.getSize());
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        stack.pop();
        System.out.println(stack.peek());
        stack.pop();
        System.out.println(stack.getSize());
        System.out.println(stack.peek());
    }
}
