package com.practice.dp;

import java.util.Arrays;

public class ClimbingStairs {
    // count all ways - fib pattern - top down
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return climbStairs(n, dp);
    }

    // same as fib
    public int climbStairs(int n, int[] dp) {
        // return 1 as base case
        if (n == 0)
            return 1;

        if (dp[n] != -1)
            return dp[n];

        int oneStep = climbStairs(n - 1, dp);

        int twoStep = 0;
        // n-2 shouldn't underflow
        if (n > 1)
            twoStep = climbStairs(n - 2, dp);

        return dp[n] = oneStep + twoStep;
    }

    // tabulation - bottom up
    public int climbStairsTab(int n) {
        int[] dp = new int[n + 1];

        // 1. store the base case manually
        dp[0] = 1;
        // n cant be 0(constraints)
        dp[1] = 1;

        // 2. loop - same statement as recursion
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    // using 3 pointers - prev2, prev and curr
    public int climbStairsVars(int n) {
        int prev = 1;
        int prev2 = 1;
        int curr = 1;

        // 2 to n
        for (int i = 2; i <= n; i++) {
            curr = prev + prev2;

            prev2 = prev;
            prev = curr;
        }

        return curr;
    }

}
