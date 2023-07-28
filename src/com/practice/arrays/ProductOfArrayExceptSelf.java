package com.practice.arrays;

public class ProductOfArrayExceptSelf {
    // optimal: can use the res arr with prefix and suffix vars - O(2n), O(1)
    // In first pass calculate the left product except self and in second calculate
    // the right and multiply it with res
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // else 0th index stays 0
        res[0] = 1;

        // use vars instead of arr
        int prefix = 1;
        int suffix = 1;

        for (int i = 1; i < n; i++) {
            prefix *= nums[i - 1];
            res[i] = prefix;
        }

        for (int i = n - 2; i >= 0; i--) {
            suffix *= nums[i + 1];
            res[i] *= suffix;
        }

        // dont need this - multiplying in the res arr itself
        // for(int i = 0; i<n; i++) {
        // res[i] = prefix[i] * suffix[i];
        // }

        return res;
    }

    // better: preprocessing of prefix and suffix array - O(3n), O(n)
    public int[] productExceptSelfBetter(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] res = new int[n];

        prefix[0] = 1;
        suffix[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        // keep this one simple - multiply prefix with one prev and suffix with one
        // ahead nums element
        for (int i = 0; i < n; i++) {
            res[i] = prefix[i] * suffix[i];
        }

        return res;
    }

    // cant use division - divide the arr prod with self

    // brute - calc prefix and suffix prod for each guy - O(n^2), O(1)
    public int[] productExceptSelfBrute(int[] nums) {
        int prefix = 1;
        int suffix = 1;
        int[] res = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                prefix *= nums[j];
            }

            for (int j = i + 1; j < nums.length; j++) {
                suffix *= nums[j];
            }

            res[i] = prefix * suffix;
            prefix = 1;
            suffix = 1;
        }

        return res;
    }
}
