package com.practice.stack;

// todo O(n) space using certain formula

class MinStack {
    // O(1), O(2n) - store (val, min val) in the stack - add elements before the
    // head and make em head
    Node head;

    public MinStack() {
        head = null;
    }

    public void push(int val) {
        int minVal = val;

        // minVal on top always
        if (head != null && head.minVal < val) {
            minVal = head.minVal;
        }

        Node newNode = new Node(val, minVal);
        newNode.next = head;
        head = newNode;
    }

    public void pop() {
        if (head == null) {
            return;
        }

        Node t = head;
        head = head.next;
        t.next = null;
    }

    public int top() {
        if (head == null) {
            return -1;
        }

        return head.val;
    }

    public int getMin() {
        if (head == null) {
            return -1;
        }

        return head.minVal;
    }
}

class Node {
    int val;
    int minVal;
    Node next;

    public Node(int v, int mV) {
        val = v;
        minVal = mV;
    }

}
