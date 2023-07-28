package com.practice.searching.binary_search;

import java.util.Arrays;

public class AggressiveCows {
    // same approach but with binary search - same search space - O(nlogn +
    // n*log(dist(max-min))), O(1)
    public static int solve(int n, int k, int[] stalls) {
        // same approach but with binary search
        Arrays.sort(stalls);

        int low = 1;
        int high = stalls[n - 1] - stalls[0];

        // finding the max barrier
        while (low <= high) {
            int mid = (low + high) / 2;
            if (canPutCows(mid, stalls, k)) {
                // maximize dist - go right
                low = mid + 1;
            } else {
                // go left
                high = mid - 1;
            }
        }

        // high will give the answer - opp polarity
        return high;
    }

    // try to give stalls one by one by keeping a min barrier - linear search on
    // range 1 to stall dist(max - min) - O(nlogn + n*dist(max-min)), O(1)
    public static int solveBrute(int n, int k, int[] stalls) {
        Arrays.sort(stalls);

        for (int i = 1; i <= stalls[n - 1] - stalls[0]; i++) {
            if (canPutCows(i, stalls, k)) {
                continue;
            } else {
                return i - 1;
            }
        }

        return -1;
    }

    // min barrier(every cow must be placed > barrier dist b/w them) - can put all
    // cows - O(n)
    public static boolean canPutCows(int barrier, int[] stalls, int k) {
        // put on the first stall - greedy
        int cows = 1;
        int lastCow = stalls[0];

        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - lastCow >= barrier) {
                // put a cow
                cows++;
                lastCow = stalls[i];
            }

            if (cows == k)
                return true;
        }

        return false;
    }

}
