package com.practice.arrays;

public class MaxSumSubarray {
    public static void main(String[] args) {
        System.out.println(maxSumSubarray(new int[] { 5, 4, -1, 7, 8 }));
    }

    // two loops - calc all possible subarrays' sum - return maxSum - O(n^2), O(1)
    private static int maxSumSubarrayBrute(int[] nums) {
        int maxSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > maxSum)
                    maxSum = sum;
            }
        }

        return maxSum;
    }

    // Kadane's algorithm
    // maxsum and currsum - reset currsum to 0 when it becomes negative (discarding
    // negative part as it dec the sum) - O(n), O(1)
    private static int maxSumSubarray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int currSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];

            if (currSum > maxSum)
                maxSum = currSum;

            if (currSum < 0)
                currSum = 0;
        }

        return maxSum;
    }

}
