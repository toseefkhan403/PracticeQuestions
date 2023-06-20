package com.practice.heap;

import java.util.PriorityQueue;

public class ConnectNRopesMinCost {
    public static void main(String[] args) {
        System.out.println(findCost(new int[] { 2, 5, 4, 8, 6, 9 }));
    }

    // cant sort and pick one by one
    // need to find 2 smallest guys every time -> use heap - O(nlogn),O(n)
    public static int findCost(int[] nums) {
        int res = 0;
        // min heap
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // add all to the heap - O(nlogn)
        for (int i = 0; i < nums.length; i++) {
            heap.offer(nums[i]);
        }

        // pick two smallest - add to cost - put rope sum back in the heap
        // O(nlogn)
        while (heap.size() > 1) {
            int join = heap.poll() + heap.poll();
            res += join;
            heap.offer(join);
        }

        return res;
    }

}
