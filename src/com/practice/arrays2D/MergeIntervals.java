package com.practice.arrays2D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {
    // brute: go ahead and merge if merges - O(n^2)

    // optimal: sort - merge with prev if merges - set to res if any changes in the
    // interval - if doesn't merge, add interval to res - O(nlogn+n+n), O(n)
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] previousInterval = res.get(res.size() - 1);

            if (intervals[i][0] <= previousInterval[1]) {
                // merge
                if (intervals[i][1] > previousInterval[1]) {
                    previousInterval[1] = intervals[i][1];
                    res.set(res.size() - 1, previousInterval);
                }
            } else {
                // add to res
                res.add(intervals[i]);
            }
        }

        // result formatting
        int[][] result = new int[res.size()][2];
        int i = 0;
        for (int[] arr : res) {
            result[i++] = arr;
        }

        return result;
    }

    // same approach without list
    public int[][] mergeI(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[][] res = new int[intervals.length][2];
        res[0] = intervals[0];

        int index = 0;

        for (int i = 1; i < intervals.length; i++) {
            int[] previousInterval = res[index];

            if (intervals[i][0] <= previousInterval[1]) {
                // merge
                if (intervals[i][1] > previousInterval[1]) {
                    previousInterval[1] = intervals[i][1];
                    res[index] = previousInterval;
                }
            } else {
                // add to res
                res[++index] = intervals[i];
            }
        }

        return Arrays.copyOfRange(res, 0, index + 1); // rest will be 0s
    }

}
