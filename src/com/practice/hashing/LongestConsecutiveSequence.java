package com.practice.hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    // brute: find seq length for every element - keep max - keep finding ++x in the
    // array - O(n^2), O(1)
    public int longestConsecutiveBrute(int[] nums) {
        if (nums.length == 0)
            return 0;

        int res = 1;
        int count = 1;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];

            while (ls(nums, ++x)) {
                count++;
                res = Math.max(res, count);
            }
            // new sequence will start - reset count
            count = 1;
        }

        return res;
    }

    public boolean ls(int[] nums, int x) {
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == x) {
                return true;
            }
        }

        return false;
    }

    // better: sort - check with previous - continue for duplicates - keep count for
    // sequence - reset if diff is not 1 or 0 - O(nlogn+n), O(n[sorting space])
    public int longestConsecutiveBetter(int[] nums) {
        if (nums.length == 0)
            return 0;

        Arrays.sort(nums);
        int count = 1;
        int res = count;

        for (int i = 1; i < nums.length; i++) {
            // inc if 1 bigger
            if (nums[i] - nums[i - 1] == 1) {
                count++;
                res = Math.max(res, count);
            }
            // continue if equal
            else if (nums[i] - nums[i - 1] == 0) {
                continue;
            }
            // if neither, sequence broken -> reset -> new sequence
            else {
                count = 1;
            }
        }

        return res;
    }

    // optimal: add to set - start seq from starting point ONLY - keep count - reset
    // when seq breaks - O(n+2n), O(n)
    public int longestConsecutiveOptimal(int[] nums) {
        if (nums.length == 0)
            return 0;

        // add to set to avoid duplicates - assuming O(1) for set
        Set<Integer> set = new HashSet<>();
        for (int i : nums)
            set.add(i);

        int count = 1;
        int res = count;

        // if a previous exist for any element, then it cannot be the starting point of
        // the seq - count from the starting pt to avoid repetitive work
        for (int i : set) {
            if (!set.contains(i - 1)) {
                // starting point of a seq
                int next = i;
                // keep finding the next guy
                while (set.contains(++next)) {
                    count++;
                }

                res = Math.max(res, count);
                // reset count
                count = 1;
            }
        }

        return res;
    }

}
