package com.practice.arrays;

public class MaxProductSubarray {
    // find all subarrays - keep max - O(n^2), O(1)
    public int maxProductBrute(int[] nums) {
        int res = Integer.MIN_VALUE;

        for (int i = 0; i < nums.length; i++) {
            int prod = 1;
            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                res = Math.max(res, prod);
            }
        }

        return res;
    }

    // observation based intuition - check from ahead and back in the same loop -
    // keep max - if prod is 0, reset it to 1 - O(n), O(1)
    // partitioned at every -ve - if two -ves, they get cancelled out - if a 0, prod
    // = 1 and take the next elements - keep max all along the way to get the ans
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;
        int n = nums.length;

        int prodAhead = 1;
        int prodBack = 1;

        for (int i = 0; i < n; i++) {
            // not nums[i] == 0
            if (prodAhead == 0)
                prodAhead = 1;
            if (prodBack == 0)
                prodBack = 1;

            prodAhead *= nums[i];
            prodBack *= nums[n - 1 - i];

            res = Math.max(res, Math.max(prodAhead, prodBack));
        }

        return res;
    }

}
