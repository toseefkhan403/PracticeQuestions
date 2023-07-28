package com.practice.dp;

class CoinChange {
    // data not uniform - thus cant use greedy - similar to Knapsack01
    // O(n*m), O(n*m+aux)
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < amount + 1; j++) {
                dp[i][j] = -1;
            }
        }

        int ans = coinRecur(n - 1, amount, coins, dp);
        // if big value is the min value -> coins cant be picked
        return ans == ((int) 1e9) ? -1 : ans;
    }

    // no need for amount == 0 check - will fill automatically
    public int coinRecur(int i, int amount, int[] coins, int[][] dp) {
        if (i == 0) {
            // pick all if can pick
            if (amount % coins[i] == 0)
                return amount / coins[i];
            else
                // cant pick - return big value
                return (int) 1e9;
        }

        if (dp[i][amount] != -1)
            return dp[i][amount];

        // not pick
        int notPick = 0 + coinRecur(i - 1, amount, coins, dp);

        // pick - need big value if finding min
        int pick = (int) 1e9;
        if (amount >= coins[i]) {
            pick = 1 + coinRecur(i, amount - coins[i], coins, dp);
        }

        return dp[i][amount] = Math.min(pick, notPick);
    }

    // similar to Knapsack01 - no aux space
    public int coinChangeTab(int[] coins, int amount) {
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < amount + 1; j++) {
                if (i == 0) {
                    // pick if can pick
                    if (j % coins[i] == 0)
                        dp[i][j] = j / coins[i];
                    else
                        dp[i][j] = (int) 1e9;

                    continue;
                }

                // not pick
                int notPick = (int) 1e9;
                if (i > 0)
                    notPick = 0 + dp[i - 1][j];

                // pick
                int pick = (int) 1e9;
                if (j >= coins[i]) {
                    pick = 1 + dp[i][j - coins[i]];
                }

                dp[i][j] = Math.min(pick, notPick);
            }
        }

        return dp[n - 1][amount] == ((int) 1e9) ? -1 : dp[n - 1][amount];
    }

}
