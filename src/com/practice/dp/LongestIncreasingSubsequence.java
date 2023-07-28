package com.practice.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {
    // dont start a new arr everytime you get a non seq guy - keep it in the single
    // arr - overwrite the just bigger value
    // binary search to find lower bound - does not give the seq - but gives correct
    // length - O(nlogn), O(n)
    public int lengthOfLISOptimal(int[] nums) {
        int n = nums.length;
        List<Integer> seq = new ArrayList<>();
        seq.add(nums[0]);

        for (int i = 1; i < n; i++) {
            int lastInserted = seq.get(seq.size() - 1);
            if (nums[i] > lastInserted)
                seq.add(nums[i]);
            else {
                // replace the guy which is just bigger - lb of binary search
                int toReplace = lowerBoundBS(seq, nums[i]);
                seq.set(toReplace, nums[i]);
            }
        }

        return seq.size();
    }

    public int lowerBoundBS(List<Integer> arr, int key) {
        int low = 0;
        int high = arr.size() - 1;
        int res = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr.get(mid) == key) {
                res = mid;
                break;
            } else if (arr.get(mid) > key) {
                if (mid == 0 || arr.get(mid - 1) < key) {
                    // found the guy
                    res = mid;
                    break;
                }

                // go left
                high = mid - 1;
            } else {
                // go right
                low = mid + 1;
            }

        }

        return res;
    }

    // without binary search
    public int lowerBound(List<Integer> arr, int key) {
        // traverse from the end - stop at first smaller prev
        int res = arr.size() - 1;
        while (res > 0) {
            if (arr.get(res - 1) < key) {
                break;
            }
            res--;
        }

        return res;
    }

    // brute: gen all subsequences - find the strictly inc ones - keep max length

    // recursion: pick and not pick with 2d dp(curr index, prev index) - O(n^2), O(n^2)
    public int lengthOfLIS(int[] nums) {
        int[][] dp = new int[nums.length][nums.length];
        int[] temp = new int[nums.length];

        Arrays.fill(temp, -1);
        Arrays.fill(dp, temp);

        return LIS(nums, 0, -1, dp);
    }

    // 1. represent in terms of index
    // 2. do recursion
    // 3. return best
    public int LIS(int[] nums, int ind, int prev, int[][] dp) {
        if (ind == nums.length)
            return 0;

        if (dp[ind][prev + 1] != -1)
            return dp[ind][prev + 1];

        int notPick = 0 + LIS(nums, ind + 1, prev, dp);
        int pick = 0;
        // pick only if condn matches
        if (prev == -1 || nums[ind] > nums[prev])
            pick += 1 + LIS(nums, ind + 1, ind, dp);

        return dp[ind][prev + 1] = Math.max(pick, notPick);
    }

    // tabulation - eliminating aux space - O(n^2), O(n^2)
    // 1. write the base case(none here)
    // 2. go reverse - +1 for coords shift
    // 3. get best and return last guy(first here)
    public int lengthOfLISTab(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 1][n + 1];

        for (int ind = n - 1; ind >= 0; ind--) {
            for (int prev = ind - 1; prev >= -1; prev--) {
                int notPick = 0 + dp[ind + 1][prev + 1];
                int pick = 0;
                // pick only if condn matches
                if (prev == -1 || nums[ind] > nums[prev])
                    pick += 1 + dp[ind + 1][ind + 1];
                dp[ind][prev + 1] = Math.max(pick, notPick);
            }
        }

        return dp[0][-1 + 1];
    }

}
