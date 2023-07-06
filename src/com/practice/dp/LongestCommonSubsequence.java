package com.practice.dp;

import java.util.Arrays;

public class LongestCommonSubsequence {
    // start from the end - two indices - go one back both if matches - otherwise go
    // one by one in each string - exponential TC
    public int longestCommonSubsequence(String text1, String text2) {
        return lcsHelper(text1, text2, text1.length() - 1, text2.length() - 1);
    }

    public int lcsHelper(String text1, String text2, int i, int j) {
        // base case - 0 length subsequence
        if (i < 0 || j < 0)
            return 0;

        if (text1.charAt(i) == text2.charAt(j)) {
            // char matches - go one behind in both
            return 1 + lcsHelper(text1, text2, i - 1, j - 1);
        }

        // doesnt match - go one back and check for each string - return max among them
        return 0 + Math.max(lcsHelper(text1, text2, i - 1, j), lcsHelper(text1, text2, i, j - 1));
    }

    // add memoization - O(n*m), O(n*m + n+m[aux])
    public int longestCommonSubsequenceDp(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n][m];

        // init dp with -1
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        return lcsHelper(text1, text2, n - 1, m - 1, dp);
    }

    public int lcsHelper(String text1, String text2, int i, int j, int[][] dp) {
        // base case - 0 length subsequence
        if (i < 0 || j < 0)
            return 0;

        // if value exists, don't compute
        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            // char matches - go one behind in both - remember the result
            return dp[i][j] = 1 + lcsHelper(text1, text2, i - 1, j - 1, dp);
        }

        // doesnt match - go one back and check for each string - return max among them
        // - remember the result
        return dp[i][j] = Math.max(lcsHelper(text1, text2, i - 1, j, dp), lcsHelper(text1, text2, i, j - 1, dp));
    }

    // todo tabulation solution

}
