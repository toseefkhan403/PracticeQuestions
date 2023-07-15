package com.practice.dp;

import java.util.Arrays;

public class HouseRobber {
    // cant use - take odd/even
    // use pick and not pick without the ds/for loop - O(n), O(n+n)
    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        return robHelper(nums.length - 1, nums, dp);
    }

    // intuition: best path for f(n) is val + dependent on best paths before it ->
    // overlapping subproblem - IMPORTANT - dp[i] signifies max profit from 0 to i
    private int robHelper(int n, int[] nums, int[] dp) {
        if (n == 0) {
            return nums[0];
        }
        if (n < 0) {
            return 0;
        }

        if (dp[n] != -1)
            return dp[n];

        // pick
        int pick = nums[n] + robHelper(n - 2, nums, dp);
        // not pick
        int notPick = robHelper(n - 1, nums, dp);

        // return best
        return dp[n] = Math.max(pick, notPick);
    }

    // O(n), O(n)
    public int robTab(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];

        for (int i = 1; i < n; i++) {
            // avoid underflow
            int pick = nums[i] + (i - 2 < 0 ? 0 : dp[i - 2]);
            int nonPick = dp[i - 1];
            dp[i] = Math.max(pick, nonPick);
        }

        // dp[i] signifies max profit from 0 to i index - thus last index gives max
        // profit in the whole arr
        return dp[n - 1];
    }

    // same as tab - use 3 pointers instead - O(n), O(1)
    public int robVars(int[] nums) {
        int n = nums.length;

        int prev = nums[0];
        int prev2 = 0;

        for (int i = 1; i < n; i++) {
            int pick = nums[i] + (i - 2 < 0 ? 0 : prev2);
            int nonPick = prev;
            int curr = Math.max(pick, nonPick);

            prev2 = prev;
            prev = curr;
        }

        // prev is the ans
        return prev;
    }

}
