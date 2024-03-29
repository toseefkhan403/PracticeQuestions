package com.practice.heap;

import java.util.PriorityQueue;

// min heap for Kth largest
// max heap for Kth smallest - PriorityQueue<>(Collections.reverseOrder())
public class KthLargestArray {
    // brute: sort it - return kth

    // better: PQ - dump everything into max heap - poll k times - ans -
    // O((n+k)logn), O(n)

    // optimal: PQ - k sized min heap - only add big guys - O(nlogk), O(k)
    public int findKthLargestI(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            if (pq.size() < k) {
                pq.offer(nums[i]);
            } else {
                if (nums[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }

        return pq.peek();
    }

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
