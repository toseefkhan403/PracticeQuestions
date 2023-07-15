package com.practice.dp;

public class UniquePaths {
    public static void main(String[] args) {
        System.out.println(uniquePathsMath(5, 5));
    }

    // 8C4 8*7*6*5/4*3*2*1 - O(m-1), O(1)
    public static int uniquePathsMath(int m, int n) {
        // m+n-2 C n-1 will give the answer
        int up = n + m - 2;
        int down = m - 1;
        // to avoid overflow
        double res = 1;

        // go from the end - not from the start - as harder to manage
        for (int i = 1; i <= down; i++)
            // does not like shorthand operator
            res = res * (up - down + i) / i;

        return (int) res;
    }

    // dp solution - tabulation - O(m*n), O(m*n)
    public int uniquePathsDp(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // first row and col should be 1
                if (i == 1 || j == 1) {
                    dp[i][j] = 1;
                } else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m][n];
    }

    // dp solution - recursion - O(m*n), O(m*n+aux)
    public int uniquePathsRecur(int m, int n, int[][] dp) {
        if (m == 1 || n == 1)
            return 1;

        if (dp[m][n] != -1)
            return dp[m][n];

        return dp[m][n] = uniquePathsRecur(m - 1, n, dp) + uniquePathsRecur(m, n - 1, dp);
    }

    // recursion - top down - 1,1 = 1,0 + 0,1 = 2 ways - O(2^(m*n)), O(aux)
    public static int uniquePathsRecur(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        if (m == 1 && n == 1) {
            return 1;
        }

        // left + up
        return uniquePathsRecur(m, n - 1) + uniquePathsRecur(m - 1, n);
    }

    // tip: draw recursive tree for better understanding
    public static int uniquePaths(int m, int n) {
        // if (m <= 0 || n <= 0) {
        // return 0;
        // }
        if (m == 1 || n == 1) {
            return 1;
        }

        return uniquePaths(m, n - 1) + uniquePaths(m - 1, n);
    }

}
