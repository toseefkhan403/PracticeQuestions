package com.practice.heap;

import java.util.PriorityQueue;

// min heap for Kth largest
// max heap for Kth smallest - PriorityQueue<>(Collections.reverseOrder())
public class KthLargestArray {
    // make k sized min heap - root is the answer
    // java -> min heap by default - O(k+(n-k)*logk[for PQ]), O(k[for PQ])
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int i = 0;
        while (i < k) {
            heap.offer(nums[i++]);
        }

        while (i < nums.length) {
            // heap shud contains all the big guys at the end of the iteration
            if (nums[i] > heap.peek()) {
                heap.poll();
                heap.offer(nums[i]);
            }

            i++;
        }

        // no need to poll the answer
        return heap.peek();
    }

}
