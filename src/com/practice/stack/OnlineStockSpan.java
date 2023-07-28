package com.practice.stack;

import java.util.Stack;

// brute: store in a list - go back and count span for every item - O(n^2)

// O(n[not n^2 as not popping everything everytime - only sometimes]), O(n)
public class OnlineStockSpan {
    Stack<Pair> stack;

    public OnlineStockSpan() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;

        // pop the previous guys(if they are smaller) and add to current span
        // the count(span) of this guy will represent the popped guys for the next
        // elements - thus can be popped
        while (!stack.isEmpty() && stack.peek().val <= price) {
            span += stack.pop().span;
        }

        stack.push(new Pair(price, span));
        return span;
    }
}

class Pair {
    int val;
    int span;

    public Pair(int v, int s) {
        val = v;
        span = s;
    }
}
