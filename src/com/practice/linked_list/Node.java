package com.practice.linked_list;

public class Node {
    int data;
    Node next;
    Node random;

    public Node(int data) {
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }
}
