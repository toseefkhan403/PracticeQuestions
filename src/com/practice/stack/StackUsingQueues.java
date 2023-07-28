package com.practice.stack;

import java.util.LinkedList;
import java.util.Queue;

// using single queue - in every push, reverse the queue - O(n), O(n)
public class StackUsingQueues {
    Queue<Integer> q;

    public StackUsingQueues() {
        q = new LinkedList<>();
    }

    // O(n)
    public void push(int x) {
        q.offer(x);

        // pop till size-1 and put to front - now pop() will act like a stack
        int size = q.size() - 1;
        for (int i = 0; i < size; i++) {
            q.offer(q.poll());
        }
    }

    // O(1)
    public int pop() {
        return q.poll();
    }

    public int top() {
        return q.peek();
    }

    public boolean empty() {
        return q.size() == 0;
    }

}
