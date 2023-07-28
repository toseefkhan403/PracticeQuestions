package com.practice.dp;

public class MinPathSum {
    // dp with memoization - O(n*m),O(n*m+aux space)
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m][n];

        // init with -1
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }

        return minRecur(grid, m - 1, n - 1, dp);
    }

    public int minRecur(int[][] grid, int i, int j, int[][] dp) {
        // base case
        if (i == 0 && j == 0) {
            return grid[i][j];
        }
        if (i < 0 || j < 0) {
            // need min sum - cant take this path so return big value
            // if 0, then this invalid path will become minimum
            return (int) 1e9;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // need to take the value of the cell you are standing on
        int up = grid[i][j] + minRecur(grid, i - 1, j, dp);
        int left = grid[i][j] + minRecur(grid, i, j - 1, dp);

        return dp[i][j] = Math.min(up, left);
    }

    // 1. copy the base case
    // 2. copy the recurrence relation
    // 3. add logical checks - return the last cell of the dp arr
    // without aux space
    public int minPathSumTab(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                    continue;
                }

                int up = (int) 1e9;
                int left = (int) 1e9;
                if (i > 0)
                    up = grid[i][j] + dp[i - 1][j];
                if (j > 0)
                    left = grid[i][j] + dp[i][j - 1];

                dp[i][j] = Math.min(up, left);
            }
        }

        return dp[m - 1][n - 1];
    }

}
