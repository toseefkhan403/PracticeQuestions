package com.practice.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

// Extension of 3sum
public class FourSum {
    // brute: find all quadruplets - sort and add to hashset to avoid repetitions -
    // return set as list - O(n^4logm) - m is the no of triplets
    public List<List<Integer>> fourSumBrute(int[] nums, int target) {
        HashSet<List<Integer>> set = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int j2 = j + 1; j2 < n; j2++) {
                    for (int k = j2 + 1; k < n; k++) {
                        if (nums[i] + nums[j] + nums[j2] + nums[k] == target) {
                            List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[j2], nums[k]);
                            // have to sort to avoid duplicates
                            Collections.sort(temp);
                            set.add(temp);
                        }
                    }
                }
            }
        }

        return new ArrayList<List<Integer>>(set);
    }

    // better: loops for a, b, c - map for checking d - when found, sort and add to
    // hashset to avoid duplicates - O(n^3logm), O(n)

    // optimised: sort it - 2 loops for a and b - 2 pointer in the rest of the arr -
    // O(nlogn + n^3), O(1)[except res - no of quads]
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);

        // till the 4th last element - pick a
        for (int i = 0; i < n - 3; i++) {
            // skipping to unique a
            if (i == 0 || nums[i] != nums[i - 1]) {
                for (int j = i + 1; j < n - 2; j++) {
                    // skipping to unique b
                    if (j == i + 1 || nums[j] != nums[j - 1]) {
                        // 2 pointer to find the complement - long to avoid int overflow
                        long comp = target;
                        comp -= nums[i];
                        comp -= nums[j];

                        int low = j + 1;
                        int high = n - 1;

                        while (low < high) {
                            long sum = nums[low] + nums[high];
                            if (sum == comp) {
                                // add to res
                                res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));

                                // skip to unique low and high
                                while (low < high && nums[low] == nums[low + 1])
                                    low++;
                                while (low < high && nums[high] == nums[high - 1])
                                    high--;

                                low++;
                                high--;
                            } else if (sum < comp) {
                                low++;
                            } else {
                                high--;
                            }
                        }
                    }
                }
            }
        }

        return res;
    }

}
