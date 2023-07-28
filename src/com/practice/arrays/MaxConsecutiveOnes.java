package com.practice.arrays;

public class MaxConsecutiveOnes {
    // single iteration - similar to Moore's voting algorithm - O(n), O(1)
    // just count ones - reset when 0 - keep max
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
                res = Math.max(res, count);
            } else {
                // reset count
                count = 0;
            }
        }

        return res;
    }

}
