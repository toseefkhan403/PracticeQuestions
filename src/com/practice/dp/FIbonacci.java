package com.practice.dp;

import java.util.Arrays;

public class FIbonacci {
    // memoization - top down - O(n), O(2n[dp arr + aux space])
    public int fib(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        return fib(n, dp);
    }

    // 1. identify overlapping subproblems - express changing vars as index in dp[]
    // 2. perform the operations - recursion
    // 3. return best possibility if already computed - else compute and store
    public int fib(int n, int[] dp) {
        if (n <= 1)
            return n;

        if (dp[n] != -1)
            return dp[n];

        return dp[n] = fib(n - 1) + fib(n - 2);
    }

    // tabulation - bottom up - O(n), O(n[dp arr])
    public int fibTab(int n) {
        if (n <= 1)
            return n;

        int[] dp = new int[n + 1];

        // 1. store the base case manually
        dp[0] = 0;
        dp[1] = 1;

        // 2. loop - same statement as recursion
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // nth fib term
        return dp[n];
    }

    // using 3 pointers - prev2, prev and curr - O(n), O(1)
    public int fibVars(int n) {
        if (n <= 1)
            return n;

        int prev = 0;
        int prev2 = 1;
        int curr = 1;

        // 1 to n
        for (int i = 1; i <= n; i++) {
            curr = prev + prev2;

            prev2 = prev;
            prev = curr;
        }

        return curr;
    }

}
