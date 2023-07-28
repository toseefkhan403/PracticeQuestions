package com.practice.dp;

public class Knapsack01 {
    // greedy wont work(there might be other combinations with larger value) - need
    // to explore all ways with dp - O(n*w),O(n*w+aux)
    static int knapSack(int W, int wt[], int val[], int n) {
        // 2d grid - match with ind, weight
        int[][] dp = new int[n][W + 1];

        // init with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < W + 1; j++) {
                dp[i][j] = -1;
            }
        }

        return knapRecur(n - 1, W, wt, val, dp);
    }

    // top down
    // recursion - pick or not pick
    static int knapRecur(int ind, int W, int wt[], int val[], int[][] dp) {
        if (ind == 0) {
            // take it if you can
            if (W >= wt[0]) {
                return val[0];
            }
            return 0;
        }

        if (dp[ind][W] != -1) {
            return dp[ind][W];
        }

        // not pick
        int notPick = 0 + knapRecur(ind - 1, W, wt, val, dp);

        // pick - minval if calc max - maxval if calc min
        int pick = Integer.MIN_VALUE;
        if (W >= wt[ind]) {
            pick = val[ind] + knapRecur(ind - 1, W - wt[ind], wt, val, dp);
        }

        return dp[ind][W] = Math.max(pick, notPick);
    }

    // tabulation - 1. copy the base case
    // 2. copy recurrence
    // 3. return last cell of dp arr
    // draw the 2d dp arr for better understanding - no aux space
    static int knapSackTab(int W, int wt[], int val[], int n) {
        // 2d grid - match with ind, weight
        int[][] dp = new int[n][W + 1];

        // base case
        // for the first item - take it if the bag allows
        for (int i = wt[0]; i < W + 1; i++)
            dp[0][i] = val[0];

        // first row contains the base case - start from the next row
        for (int i = 1; i < n; i++) {
            // can start from 1 because first col will always be 0
            for (int j = 1; j < W + 1; j++) {
                int notPick = 0 + dp[i - 1][j];

                // pick
                int pick = Integer.MIN_VALUE;
                if (i > 0 && j >= wt[i])
                    pick = val[i] + dp[i - 1][j - wt[i]];

                dp[i][j] = Math.max(pick, notPick);
            }
        }

        // last cell
        return dp[n - 1][W];
    }

}
