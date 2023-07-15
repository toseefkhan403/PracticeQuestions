package com.practice.dp;

public class Knapsack01 {
    // greedy wont work(there might be other combinations with larger value)
    // 2d dp arr - O(n),O(2n)
    static int knapSack(int W, int wt[], int val[], int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = -1;
            }
        }

        return knapRecur(0, W, wt, val, dp);
    }

    // need to explore all ways with dp
    // recursion - pick or not pick
    static int knapRecur(int i, int W, int wt[], int val[], int[][] dp) {
        if (i == wt.length) {
            return 0;
        }

        if (dp[i][W] != -1)
            return dp[i][W];

        int left = 0;
        // pick
        if (W - wt[i] >= 0) {
            left = val[i] + knapRecur(i + 1, W - wt[i], wt, val, dp);
        }

        int right = knapRecur(i + 1, W, wt, val, dp);

        return dp[i][W] = Math.max(left, right);
    }

    // todo tabulation

}
