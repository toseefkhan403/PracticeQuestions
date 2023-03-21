package com.practice.arrays;

import java.util.HashMap;

public class TwoSum {

    // Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
    public static void main(String[] args) throws Exception {
        int[] nums = new int[]{2,7,11,15};
        int target = 9;
        int[] res = twoSumOpti(nums,target);

        for(int r : res) {
            System.out.println(r);
        }
    }

    // Brute force: two loops; break if target found
    public static int[] twoSumBrute(int[] nums, int target) {
        int[] res = new int[2];
        for(int i=0; i<nums.length; i++) {
            for(int j=i+1; j<nums.length; j++) {
                if(nums[i]+nums[j] == target) {
                    res[0] = i;
                    res[1] = j;
                    break;
                }
            }
        }

        return res;
    }

    // 2 pass: hashmap - use complement = target - curr element; fill the hashmap and use .get() in a second loop to get the complement; also don't use the same index twice
    public static int[] twoSumOpti2(int[] nums, int target) {
        HashMap<Integer, Integer> res = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            res.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(res.get(complement) != null && res.get(complement) != i) {
                return new int[]{i, res.get(complement)};
            }
        }

        return new int[2];
    }

    // 1 pass: can do above in a single loop as well - but .get() first and .put() later in the loop
    public static int[] twoSumOpti(int[] nums, int target) {
        HashMap<Integer, Integer> res = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(res.get(complement) != null && res.get(complement) != i) {
                return new int[]{res.get(complement), i};
            }
            res.put(nums[i], i);
        }

        return new int[2];
    }

}
