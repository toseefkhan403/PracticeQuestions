package com.practice.stack;

import java.util.Stack;

class QueueUsingStacks {
    // push - 1.s1 to s2 - 2.add x to s1 - 3.s2 to s1
    Stack<Integer> st1;
    Stack<Integer> st2;

    public QueueUsingStacks() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    // O(2n)
    public void push(int x) {
        while (!st1.isEmpty()) {
            st2.push(st1.pop());
        }

        st1.push(x);

        while (!st2.isEmpty()) {
            st1.push(st2.pop());
        }
    }

    public int pop() {
        return st1.pop();
    }

    public int peek() {
        return st1.peek();
    }

    public boolean empty() {
        return st1.isEmpty();
    }
}

class QueueUsingStacksOptimal {
    // O(1) amortized(almost) pop - input and output stacks
    Stack<Integer> input;
    Stack<Integer> output;

    public QueueUsingStacksOptimal() {
        input = new Stack<>();
        output = new Stack<>();
    }

    public void push(int x) {
        input.push(x);
    }

    // if output is empty, transfer to output arr and pop from there - O(1)
    // amortized(sometimes O(n)) pop and peek
    public int pop() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        return output.pop();
    }

    public int peek() {
        if (output.isEmpty()) {
            while (!input.isEmpty()) {
                output.push(input.pop());
            }
        }

        return output.peek();
    }

    // empty if both stacks are empty
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }

}
