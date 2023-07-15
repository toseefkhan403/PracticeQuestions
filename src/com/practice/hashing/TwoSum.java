package com.practice.hashing;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) throws Exception {
        int[] nums = new int[] { 2, 7, 11, 15 };
        int target = 9;
        int[] res = twoSumOpti(nums, target);

        for (int r : res) {
            System.out.println(r);
        }
    }

    // Brute force: two loops; break if target found - O(n^2), O(1)
    public static int[] twoSumBrute(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }

        return res;
    }

    // 2 pass: hashmap - use complement = target - curr element; fill the hashmap
    // and use .get() in a second loop to get the complement; also don't use the
    // same index twice - O(2n), O(n)
    public static int[] twoSumOpti2(int[] nums, int target) {
        // value, index
        HashMap<Integer, Integer> res = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            res.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (res.get(complement) != null && res.get(complement) != i) {
                return new int[] { i, res.get(complement) };
            }
        }

        return new int[2];
    }

    // 1 pass: can do above in a single loop as well - but .get() first and .put()
    // later in the loop - O(n), O(n)
    public static int[] twoSumOpti(int[] nums, int target) {
        // value, index
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int comp = target - nums[i];

            if (map.containsKey(comp)) {
                return new int[] { map.get(comp), i };
            }

            map.put(nums[i], i);
        }

        return new int[] { -1, -1 };
    }

}
