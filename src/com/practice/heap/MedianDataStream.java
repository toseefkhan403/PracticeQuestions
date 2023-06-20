package com.practice.heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianDataStream {
    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    // O(nlogn), O(n[PQs storing every element])
    public MedianDataStream() {
        // containing first half of the numbers(smaller numbers)
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        // containing second half of the numbers(larger numbers)
        minHeap = new PriorityQueue<>();
        // middle will give the median
    }

    // O(logn)
    public void addNum(int num) {
        // why >= - because storing smaller nos in maxheap
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }

        // either should be equal or maxheap shud be 1 more than the minheap
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    // O(1)
    public double findMedian() {
        if (maxHeap.size() != minHeap.size()) {
            // odd no of elements - first guy of max heap is the ans
            return maxHeap.peek();
        } else {
            // even no of elements - avg of first guy of both the heaps
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
    }

}
