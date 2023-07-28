package com.practice.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    // brute: k sized sliding window - find min in each window - O(n*k)

    // better: k sized PQ - max heap - delete front as you go - O(n*logk)

    // optimal: use deque with nge concept - maintain dec order - O(2n[might need to
    // pop the full deque]), O(k[deque])
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        int ri = 0;

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < nums.length; i++) {
            // remove outofbounds guys(windowstart)
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }

            // maintain dec order(first element should be the max always) - remove small
            // guys from the end
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()])
                deque.pollLast();

            // storing index to remove windowstart later - offer() not push()[adds to head]
            deque.offer(i);

            if (i >= k - 1) {
                // first element is the max always
                res[ri++] = nums[deque.peekFirst()];
            }
        }

        return res;
    }

}
